package com.java.tutorial.project.service.impl;

import com.alibaba.fastjson2.JSON;
import com.java.tutorial.project.common.enumtype.ConnectionTypeEnum;
import com.java.tutorial.project.common.enumtype.SceneEnum;
import com.java.tutorial.project.common.vo.ContentVo;
import com.java.tutorial.project.common.vo.MessageVo;
import com.java.tutorial.project.infrastucture.entity.ConnectionEntity;
import com.java.tutorial.project.service.ConnectionInfoService;
import com.java.tutorial.project.service.MessageService;
import com.java.tutorial.project.service.SseEmitterService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.data.id.IdUtil;
import org.dromara.hutool.core.map.MapUtil;
import org.dromara.hutool.core.text.StrUtil;
import org.dromara.hutool.http.meta.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import static com.java.tutorial.project.common.util.DateTimeUtil.FORMAT_DATE_TIME;
import static com.java.tutorial.project.common.util.DateTimeUtil.FORMAT_LAST_DATE_TIME;
import static com.java.tutorial.project.common.util.DateTimeUtil.TIMEOUT;

/**
 * @author meta
 */
@Service
@Slf4j
public class SseEmitterServiceImpl implements SseEmitterService {

    /**
     * 容器，保存连接，用于输出返回 ;可使用其他方法实现
     */
    private static final Map<String, SseEmitter> SSE_CACHE = new ConcurrentHashMap<>();

    @Resource
    private ConnectionInfoService connectionInfoService;

    @Resource
    private MessageService messageService;

    /**
     * 根据客户端id获取SseEmitter对象
     *
     * @param clientId 客户端ID
     */
    @Override
    public SseEmitter getSseEmitterByClientId(String clientId) {
        return SSE_CACHE.get(clientId);
    }

    @Override
    public Set<String> getAllClient() {
        return SSE_CACHE.keySet();
    }

    /**
     * 创建连接
     *
     * @param clientId 客户端ID
     */
    @Override
    public SseEmitter createConnect(String clientId, Integer type) {
        // 判断连接类型
        ConnectionTypeEnum connectionTypeEnum = ConnectionTypeEnum.getByCode(type);
        // 是否需要给客户端推送ID
        if (StrUtil.isBlank(clientId)) {
            clientId = IdUtil.simpleUUID();
        }
        SseEmitter sseEmitter = buildSseEmitter(clientId);
        SSE_CACHE.put(clientId, sseEmitter);
        log.info("创建新的sse连接，当前用户：{}, 累计用户:{}", clientId, SSE_CACHE.size());
        try {

            // 注册成功返回用户信息
            MessageVo messageVo = new MessageVo();
            messageVo.setClientId(clientId);
            messageVo.setType(connectionTypeEnum.getCode());
            SseEmitter.SseEventBuilder data = SseEmitter.event().id(String.valueOf(HttpStatus.HTTP_CREATED))
                .data(messageVo, MediaType.APPLICATION_JSON);
            sseEmitter.send(data);
            // 保存连接信息
            ConnectionEntity connectionEntity =
                ConnectionEntity.builder().clientId(clientId).createTime(FORMAT_DATE_TIME)
                    .type(connectionTypeEnum.getCode()).lastContactTime("").build();

            //保存连接信息到MongoDB中
            connectionInfoService.create(connectionEntity);
        } catch (IOException e) {
            log.error("创建长链接异常，客户端ID:{}   异常信息:{}", clientId, e.getMessage());
            throw new RuntimeException("创建链接异常");
        }
        return sseEmitter;
    }

    @Override
    public SseEmitter buildSseEmitter(String clientId) {
        // 设置超时时间，0表示不过期。默认30秒，超过时间未完成会抛出异常：AsyncRequestTimeoutException
        //当前设置10分钟
        SseEmitter sseEmitter = new SseEmitter(TIMEOUT);
        // 注册回调
        // 长链接完成后回调接口(即关闭连接时调用)
        sseEmitter.onCompletion(completionCallBack(clientId));
        // 连接超时回调
        sseEmitter.onTimeout(timeoutCallBack(clientId));
        // 推送消息异常时，回调方法
        sseEmitter.onError(errorCallBack(clientId));
        return sseEmitter;
    }

    /**
     * 发送消息给所有客户端
     *
     * @param contentVo 消息内容
     */
    @Override
    public void sendMessageToAllClient(ContentVo contentVo) {

        // 判断发送的消息是否为空
        if (Objects.isNull(contentVo)) {
            throw new RuntimeException("消息内容不能为空");
        }

        if (MapUtil.isEmpty(SSE_CACHE)) {
            throw new RuntimeException("");
        }

        for (Map.Entry<String, SseEmitter> entry : SSE_CACHE.entrySet()) {
            MessageVo messageVo = new MessageVo();
            messageVo.setClientId(entry.getKey());
            messageVo.setContent(contentVo);
            sendMsgToClientByClientId(entry.getKey(), messageVo, entry.getValue());
        }
    }

    /**
     * 给指定客户端发送消息
     *
     * @param messageVo 消息内容
     */
    @Override
    public void sendMessageToOneClient(MessageVo messageVo) {
        String clientId = messageVo.getClientId();
        sendMsgToClientByClientId(clientId, messageVo, SSE_CACHE.get(clientId));
    }

    @Override
    public void sendMessageToManyClient(MessageVo msg) {
        if (MapUtil.isEmpty(SSE_CACHE)) {
            return;
        }
        String client = msg.getClientId();
        int count = 1;
        for (Map.Entry<String, SseEmitter> entry : SSE_CACHE.entrySet()) {
            String[] str = entry.getKey().split("@");
            if (str.length > 1 && str[0].equals(client)) {
                MessageVo messageVo = new MessageVo();
                messageVo.setClientId(entry.getKey());
                messageVo.setContent(msg.getContent());
                sendMsgToClientByClientId(entry.getKey(), messageVo, entry.getValue());
            }
        }
        log.info("本次数据推送给了{}个客户端", count);
    }

    /**
     * 关闭连接
     *
     * @param clientId 客户端ID
     */
    @Override
    public void closeConnect(String clientId) {
        SseEmitter sseEmitter = SSE_CACHE.get(clientId);
        if (sseEmitter != null) {
            Integer type = connectionInfoService.getClient(clientId) == null ? 1
                : connectionInfoService.getClient(clientId).getType();
            //complete之后会走completionCallBack回调
            sseEmitter.complete();
            //插件主动断开连接发送飞书通知
            removeUser(clientId);
            messageService.sendMessage(clientId, type, SceneEnum.CLOSE);
        }
    }

    /**
     * 推送消息到客户端 此处做了推送失败后，重试推送机制，可根据自己业务进行修改
     *
     * @param clientId  客户端ID
     * @param messageVo 推送信息，此处结合具体业务，定义自己的返回值即可
     **/
    private void sendMsgToClientByClientId(String clientId, MessageVo messageVo, SseEmitter sseEmitter) {
        if (sseEmitter == null) {
            log.error("推送消息失败：客户端{}未创建长链接,失败消息:{}", clientId, messageVo.toString());
            Integer type = messageVo.getType() == null ? 0 : messageVo.getType();
            messageService.sendMessage(clientId, type, SceneEnum.NO_CONN);
            return;
        }
        SseEmitter.SseEventBuilder sendData =
            SseEmitter.event().id(String.valueOf(HttpStatus.HTTP_OK)).data(messageVo, MediaType.APPLICATION_JSON);
        try {
            sseEmitter.send(sendData);
            log.info("推送消息成功,消息body体:{}", messageVo);
            //推送成功，更新上次通信时间
            ConnectionEntity client = connectionInfoService.getClient(clientId);
            if (Objects.nonNull(client)) {
                connectionInfoService.create(
                    ConnectionEntity.builder().clientId(clientId).createTime(client.getCreateTime())
                        .type(client.getType()).lastContactTime(FORMAT_LAST_DATE_TIME).build());
            }
        } catch (IOException e) {
            // 推送消息失败，记录错误日志，进行重推
            log.error("推送消息失败：{},尝试进行重推", JSON.toJSONString(messageVo.toString()));
            boolean isSuccess = false;
            // 推送消息失败后，每隔5s推送一次，推送3次
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(5000);
                    sseEmitter = SSE_CACHE.get(clientId);
                    if (sseEmitter == null) {
                        log.error("{}的第{}次消息重推失败，未创建长链接", clientId, i + 1);
                    } else {
                        sseEmitter.send(sendData);
                        log.info("{}的第{}次消息重推成功,消息body体{}", clientId, i + 1, messageVo.toString());
                        isSuccess = true;
                        break;
                    }
                } catch (Exception ex) {
                    log.error("{}的第{}次消息重推失败", clientId, i + 1, ex);
                }
            }
            if (!isSuccess) {
                ConnectionEntity client = connectionInfoService.getClient(clientId);
                //若还是失败，则飞书告警
                messageService.sendMessage(clientId, client.getType(), SceneEnum.ERROR);
            }
        }
    }

    /**
     * 长链接完成后回调接口(即关闭连接时调用)
     *
     * @param clientId 客户端ID
     **/
    private Runnable completionCallBack(String clientId) {
        return () -> {
            log.info("结束连接：{}", clientId);
            removeUser(clientId);
        };
    }

    /**
     * 连接超时时调用
     *
     * @param clientId 客户端ID
     **/
    private Runnable timeoutCallBack(String clientId) {
        return () -> {
            log.warn("连接超时：{}", clientId);
            ConnectionEntity client = connectionInfoService.getClient(clientId);
            removeUser(clientId);
            //连接超时会断开连接，飞书发送通知
            messageService.sendMessage(clientId, client.getType(), SceneEnum.EXPIRED);
        };
    }

    /**
     * 推送消息异常时，回调方法
     *
     * @param clientId 客户端ID
     **/
    private Consumer<Throwable> errorCallBack(String clientId) {
        return throwable -> {
            log.error("SseEmitterServiceImpl[errorCallBack]：连接异常,客户端ID:{}", clientId);
            ConnectionEntity client = connectionInfoService.getClient(clientId);
            //推送失败发送飞书消息
            messageService.sendMessage(clientId, client.getType(), SceneEnum.ERROR);
        };
    }

    /**
     * 移除用户连接
     *
     * @param clientId 客户端ID
     **/
    private void removeUser(String clientId) {
        SSE_CACHE.remove(clientId);
        //从Mongo中移除
        connectionInfoService.deleteByClientId(clientId);
        log.info("SseEmitterServiceImpl[removeUser]:移除用户clientId：{}", clientId);
    }
}

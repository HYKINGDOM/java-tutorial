package com.java.coco.utils.http;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.java.coco.utils.StopWatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * http 封装工具类 JDK8 起
 *
 * @author meta
 */
@Slf4j
public class HttpClientUtil {

    /**
     * 请求最大次数
     */
    public static final int REQUEST_MAX_NUM = 5000;

    /**
     * 初始化时间
     */
    public static final int INITIAL_DELAY = 10 * 1000;

    /**
     * 检测连接是否可用的时间间隔
     */
    public static final int PERIOD = 5 * 1000;

    /**
     * 默认请求头 Content-Type
     */
    public static final String CONTENT_TYPE_JSON = "application/json";

    /**
     * 默认请求头 Content-Type
     */
    public static final String MIME_TYPE = "text/plain";

    /**
     * 默认请求超时时间
     */
    public static final int SO_TIMEOUT = 5000;

    /**
     * 线程池核心线程数
     */
    public static final int CORE_POOL_SIZE = 1;
    /**
     * 连接空闲时间
     */
    public static final int IDLE_TIMEOUT = 30;
    /**
     * 定时器名称
     */
    public static final String CLOSE_EXPIRED_CONNECTIONS_SCHEDULE_POOL = "close-expired-connections-schedule-pool-%d";
    /**
     * 参数拼接符
     */
    public static final String STRING = "?";
    /**
     * 参数拼接符
     */
    public static final char CHAR = '?';
    /**
     * 空字符串
     */
    public static final String EMPTY_BODY = "";
    /**
     * 参数格式化
     */
    public static final String FORMAT_NAME_VALUE = "'%s'='%s'";
    /**
     * 连接池最大连接数 默认值：20
     */
    private final static int MAX_TOTAL = 50;
    /**
     * 每个路由最大连接数 默认值：2
     */
    private final static int MAX_PRE_ROUTE = 4;
    /**
     * 从线程池中获取线程超时时间
     */
    private static final int CONNECTION_REQUEST_TIMEOUT = 3000;
    /**
     * 连接超时时间
     */
    private static final int CONNECT_TIMEOUT = 3000;
    /**
     * 设置数据超时时间
     */
    private static final int SOCKET_TIMEOUT = 10000;
    /**
     * 连接池对象
     */
    private static PoolingHttpClientConnectionManager connectionManager = null;
    /**
     * 请求参数配置
     */
    private static RequestConfig requestConfig;
    /**
     * httpclient对象
     */
    private static CloseableHttpClient httpclient;

    /**
     * 单例模式创建连接池
     */
    private static void init() {
        synchronized (HttpClientUtil.class) {
            if (httpclient == null) {
                // 连接池管理器
                connectionManager = new PoolingHttpClientConnectionManager();
                // 设置最大连接数
                connectionManager.setMaxTotal(MAX_TOTAL);
                // 设置每个路由的最大连接数
                connectionManager.setDefaultMaxPerRoute(MAX_PRE_ROUTE);
                // http请求线程池，最大连接数
                ConnectionConfig connConfig = ConnectionConfig.custom().setCharset(StandardCharsets.UTF_8).build();
                // 设置socket参数
                SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(SO_TIMEOUT).build();
                // 设置连接参数
                connectionManager.setDefaultConnectionConfig(connConfig);
                // 设置socket参数
                connectionManager.setDefaultSocketConfig(socketConfig);
                // 连接池最大生成连接数
                connectionManager.setMaxTotal(REQUEST_MAX_NUM);
                // 默认设置route最大连接数
                connectionManager.setDefaultMaxPerRoute(REQUEST_MAX_NUM);
                //设置请求参数 设置默认的请求参数
                getDefaultRequestConfig();
                // 创建builder
                HttpClientBuilder builder = HttpClients.custom();
                //管理器是共享的，它的生命周期将由调用者管理，并且不会关闭
                //否则可能出现Connection pool shut down异常
                builder.setConnectionManager(connectionManager);
                // 共享连接池
                builder.setConnectionManagerShared(true);
                // 长连接策略
                builder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
                // requestConfig 已经被设置为默认值getDefaultRequestConfig()
                builder.setDefaultRequestConfig(requestConfig);
                // 设置重试策略
                builder.setRetryHandler(new RequestPoolRetryHandle());
                // 创建httpClient,
                httpclient = builder.build();
                // 创建线程池
                BasicThreadFactory basicThreadFactory =
                    new BasicThreadFactory.Builder().namingPattern(CLOSE_EXPIRED_CONNECTIONS_SCHEDULE_POOL).daemon(true)
                        .build();
                // 启动定时器，定时回收过期的连接
                ScheduledExecutorService executorService =
                    new ScheduledThreadPoolExecutor(CORE_POOL_SIZE, basicThreadFactory);
                // 每5分钟执行一次
                executorService.scheduleAtFixedRate(() -> {
                    // 关闭过期的链接
                    connectionManager.closeExpiredConnections();
                    // 选择关闭 空闲30秒的链接
                    connectionManager.closeIdleConnections(IDLE_TIMEOUT, TimeUnit.SECONDS);
                }, INITIAL_DELAY, PERIOD, TimeUnit.HOURS);

            }
        }
    }

    /**
     * 请求连接池失败重试策略
     */
    public static class RequestPoolRetryHandle implements HttpRequestRetryHandler {
        /**
         * 重试策略的重试次数
         */
        public static final int RETRY_LIMIT = 3;

        Logger logger = LoggerFactory.getLogger(RequestPoolRetryHandle.class);

        //请求失败时,进行请求重试
        @Override
        public boolean retryRequest(IOException e, int i, HttpContext httpContext) {
            if (i > RETRY_LIMIT) {
                //重试超过3次,放弃请求
                logger.error("retry has more than 3 time, give up request");
                return false;
            }
            if (e instanceof NoHttpResponseException) {
                //服务器没有响应,可能是服务器断开了连接,应该重试
                logger.error("receive no response from server, retry");
                return true;
            }
            if (e instanceof SSLHandshakeException) {
                // SSL握手异常
                logger.error("SSL hand shake exception");
                return false;
            }
            if (e instanceof InterruptedIOException) {
                //超时
                logger.error("InterruptedIOException");
                return false;
            }
            if (e instanceof UnknownHostException) {
                // 服务器不可达
                logger.error("server host unknown");
                return false;
            }
            if (e instanceof SSLException) {
                logger.error("SSLException");
                return false;
            }

            HttpClientContext context = HttpClientContext.adapt(httpContext);
            HttpRequest request = context.getRequest();
            //如果请求不是关闭连接的请求
            return !(request instanceof HttpEntityEnclosingRequest);
        }
    }

    /**
     * 设置请求配置 #setConnectTimeout 设置连接超时 #setSocketTimeout 设置读取超时 #setConnectionRequestTimeout 设置从连接池获取连接实例的超时
     *
     * @param socketTimeout 连接超时时间，单位毫秒
     */
    private static void getRequestConfig(int socketTimeout) {

        if (socketTimeout != SOCKET_TIMEOUT) {
            requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(socketTimeout)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT).build();
        } else {
            getDefaultRequestConfig();
        }
    }

    /**
     * 获取默认的请求配置
     */
    private static void getDefaultRequestConfig() {
        requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT)
            .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT).build();
    }

    /**
     * 从连接池中获取httpClient实例
     *
     * @return CloseableHttpClient
     */
    private static CloseableHttpClient getClientFromPool() {
        if (httpclient == null) {
            init();
        }
        return httpclient;
    }

    /**
     * 发送post请求，包含表单参数
     *
     * @param rootUrl       请求地址
     * @param params        URL参数
     * @param forms         表单参数
     * @param headers       请求头
     * @param socketTimeout 连接超时时间，单位毫秒
     * @return response
     */
    public static String doPostWithForm(String rootUrl, Map<String, Object> params, Map<String, Object> forms,
        Map<String, String> headers, int socketTimeout) {
        StopWatch clock = new StopWatch(rootUrl);
        clock.start();
        String realUrl = rootUrl + generatorParamString(params);
        HttpPost httpPost = new HttpPost(realUrl);
        for (String key : headers.keySet()) {
            httpPost.setHeader(key, headers.get(key));
        }
        getRequestConfig(socketTimeout);
        httpPost.setConfig(requestConfig);
        CloseableHttpClient httpclient = getClientFromPool();
        String response = EMPTY_BODY;
        if (Objects.nonNull(forms)) {
            //创建 MultipartEntityBuilder,以此来构建我们的参数
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            //设置字符编码，防止乱码
            ContentType contentType = ContentType.create(MIME_TYPE, StandardCharsets.UTF_8);
            for (Map.Entry<String, Object> entry : forms.entrySet()) {
                if (entry.getValue().getClass().isInstance(EMPTY_BODY)) {
                    //填充我们的文本内容，这里相当于input 框中的 name 与value
                    entityBuilder.addPart(entry.getKey(), new StringBody(entry.getValue().toString(), contentType));
                } else {
                    //上传我们的文件
                    entityBuilder.addBinaryBody(entry.getKey(), (File)entry.getValue());
                }
                //参数组装
                httpPost.setEntity(entityBuilder.build());
            }
        }
        try (CloseableHttpResponse responseDto = httpclient.execute(httpPost)) {
            clock.stop();
            response = EntityUtils.toString(responseDto.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("doPostWithForm 请求异常： url:{}, params:{}, response:{}, exception:{}", realUrl, params,
                response, e);
        }
        log.debug("doPostWithForm url:{},costs:{}ms, response: {}", realUrl, clock.getTotalTimeMillis(), response);
        return response;
    }

    /**
     * @param rootUrl       根路径
     * @param params        请求参数
     * @param body          请求body体
     * @param headers       请求header
     * @param socketTimeout 超时时间
     * @return 返回报文
     */
    public static String doPost(String rootUrl, Map<String, Object> params, String body, Map<String, String> headers,
        int socketTimeout) {
        StopWatch clock = new StopWatch(rootUrl);
        clock.start();
        String realUrl = rootUrl + generatorParamString(params);
        HttpPost httpPost = new HttpPost(realUrl);
        for (String key : headers.keySet()) {
            httpPost.setHeader(key, headers.get(key));
        }
        getRequestConfig(socketTimeout);
        httpPost.setConfig(requestConfig);
        CloseableHttpClient httpclient = getClientFromPool();
        String response = EMPTY_BODY;
        if (StrUtil.isNotEmpty(body)) {
            StringEntity stringEntity = new StringEntity(body, StandardCharsets.UTF_8);
            stringEntity.setContentEncoding(StandardCharsets.UTF_8.name());
            stringEntity.setContentType(CONTENT_TYPE_JSON);
            httpPost.setEntity(stringEntity);
        }
        try (CloseableHttpResponse responseDto = httpclient.execute(httpPost)) {
            clock.stop();
            response = EntityUtils.toString(responseDto.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("doPost 请求异常： url:{}, params:{}, response:{}, exception:{}", realUrl, params, response, e);
        }
        log.debug("doPost url:{}, costs:{}ms, response: {}", realUrl, clock.getTotalTimeMillis(), response);
        return response;
    }

    /**
     * 文件发送Post请求
     *
     * @param rootUrl       根路径
     * @param params        请求参数
     * @param headers       请求header
     * @param socketTimeout 超时时间
     * @param file          文件
     * @return 返回报文
     */
    public static String doPostFile(String rootUrl, Map<String, Object> params, Map<String, String> headers,
        int socketTimeout, File file) {
        StopWatch clock = new StopWatch(rootUrl);
        clock.start();
        rootUrl = handleUrl(rootUrl, params);
        String realUrl = rootUrl + generatorParamString(params);
        HttpPost httpPost = new HttpPost(realUrl);
        for (String key : headers.keySet()) {
            httpPost.setHeader(key, headers.get(key));
        }
        getRequestConfig(socketTimeout);
        httpPost.setConfig(requestConfig);
        CloseableHttpClient httpclient = getClientFromPool();
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.setCharset(StandardCharsets.UTF_8);
        multipartEntityBuilder.addBinaryBody("file", file);
        HttpEntity httpEntity = multipartEntityBuilder.build();
        httpPost.setEntity(httpEntity);

        String response = EMPTY_BODY;
        try (CloseableHttpResponse responseDto = httpclient.execute(httpPost)) {
            clock.stop();
            response = EntityUtils.toString(responseDto.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("doPostFile 文件上传请求异常： url:{}, params:{}, response:{}, exception:{}", realUrl, params,
                response, e);
        }
        log.debug("doPostFile file post url: {}, costs: {} ms, response: {}", realUrl, clock.getTotalTimeMillis(),
            response);
        return response;
    }

    /**
     * GET 请求
     *
     * @param rootUrl       rootUrl
     * @param params        请求参数
     * @param headers       请求header
     * @param socketTimeout 超时时间
     * @return 返回报文
     */
    public static String doGet(String rootUrl, Map<String, Object> params, Map<String, String> headers,
        int socketTimeout) {
        StopWatch clock = new StopWatch(rootUrl);
        clock.start();
        String realUrl = rootUrl + generatorParamString(params);
        HttpGet httpGet = new HttpGet(realUrl);
        for (String key : headers.keySet()) {
            httpGet.setHeader(key, headers.get(key));
        }
        getRequestConfig(socketTimeout);
        httpGet.setConfig(requestConfig);
        CloseableHttpClient httpclient = getClientFromPool();
        String response = EMPTY_BODY;
        try (CloseableHttpResponse responseDto = httpclient.execute(httpGet)) {
            clock.stop();
            response = EntityUtils.toString(responseDto.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("doGet 请求异常： url:{}, params:{}, response:{}, exception:{}", realUrl, params, response, e);
        }
        log.debug("doGet url:{},costs:{}ms, response: {}", realUrl, clock.getTotalTimeMillis(), response);
        return response;
    }

    /**
     * delete请求
     *
     * @param url
     * @param headers
     * @param body
     * @param socketTimeout
     * @return
     */
    public static String doDelete(String url, Map<String, String> headers, String body, int socketTimeout) {
        StopWatch clock = new StopWatch(url);
        clock.start();
        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(url);
        for (String key : headers.keySet()) {
            httpDelete.setHeader(key, headers.get(key));
        }

        if (StrUtil.isNotBlank(body)) {
            StringEntity stringEntity = new StringEntity(body, StandardCharsets.UTF_8);
            stringEntity.setContentEncoding(StandardCharsets.UTF_8.name());
            stringEntity.setContentType(CONTENT_TYPE_JSON);
            httpDelete.setEntity(stringEntity);
        }

        getRequestConfig(socketTimeout);
        httpDelete.setConfig(requestConfig);
        CloseableHttpClient httpclient = getClientFromPool();
        String response = EMPTY_BODY;
        try (CloseableHttpResponse responseDto = httpclient.execute(httpDelete)) {
            clock.stop();
            response = EntityUtils.toString(responseDto.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("doDelete 请求异常： url:{}, response:{}, exception:{}", url, response, e);
        }
        log.debug("doDelete url:{},costs:{}ms, response: {}", url, clock.getTotalTimeMillis(), response);
        return response;
    }

    /**
     * 自定义的 HttpDeleteWithBody 类
     */
    public static class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
        public static final String METHOD_NAME = "DELETE";

        public HttpDeleteWithBody(final String uri) {
            super();
            setURI(URI.create(uri));
        }

        @Override
        public String getMethod() {
            return METHOD_NAME;
        }
    }

    /**
     * put请求
     *
     * @param url
     * @param body
     * @param headers
     * @param socketTimeout
     * @return
     */
    public static String doPut(String url, String body, Map<String, String> headers, int socketTimeout) {
        StopWatch clock = new StopWatch(url);
        clock.start();
        HttpPut httpPut = new HttpPut(url);
        for (String headerName : headers.keySet()) {
            httpPut.setHeader(headerName, headers.get(headerName));
        }
        getRequestConfig(socketTimeout);
        httpPut.setConfig(requestConfig);
        CloseableHttpClient httpclient = getClientFromPool();
        String response = EMPTY_BODY;
        if (StrUtil.isNotEmpty(body)) {
            StringEntity stringEntity = new StringEntity(body, StandardCharsets.UTF_8);
            stringEntity.setContentEncoding(StandardCharsets.UTF_8.name());
            stringEntity.setContentType(CONTENT_TYPE_JSON);
            httpPut.setEntity(stringEntity);
        }
        try (CloseableHttpResponse responseDto = httpclient.execute(httpPut)) {
            clock.stop();
            response = EntityUtils.toString(responseDto.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("doPut 请求异常： url:{}, body:{}, response:{}, exception:{}", url, body, response, e);
        }
        log.debug("doPut url:{}, costs:{}ms, response: {}", url, clock.getTotalTimeMillis(), response);
        return response;
    }

    /**
     * Patch请求
     *
     * @param rootUrl
     * @param params
     * @param body
     * @param headers
     * @param socketTimeout
     * @return
     */
    public static String doPatch(String rootUrl, Map<String, Object> params, String body, Map<String, String> headers,
        int socketTimeout) {
        StopWatch clock = new StopWatch(rootUrl);
        clock.start();
        String realUrl = rootUrl + generatorParamString(params);
        HttpPatch httpPatch = new HttpPatch(realUrl);
        for (String key : headers.keySet()) {
            httpPatch.setHeader(key, headers.get(key));
        }
        getRequestConfig(socketTimeout);
        httpPatch.setConfig(requestConfig);
        String response = EMPTY_BODY;
        CloseableHttpClient httpclient = getClientFromPool();
        if (StrUtil.isNotEmpty(body)) {
            StringEntity stringEntity = new StringEntity(body, StandardCharsets.UTF_8);
            stringEntity.setContentEncoding(StandardCharsets.UTF_8.name());
            stringEntity.setContentType(CONTENT_TYPE_JSON);
            httpPatch.setEntity(stringEntity);
        }

        try (CloseableHttpResponse responseDto = httpclient.execute(httpPatch)) {
            clock.stop();
            response = EntityUtils.toString(responseDto.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("doPatch 请求异常： url:{}, body:{}, response:{}, exception:{}", realUrl, body, response, e);
        }

        log.debug("doPatch url:{}, costs:{}ms, response: {}", realUrl, clock.getTotalTimeMillis(), response);
        return response;
    }

    /**
     * 生成参数字符串
     *
     * @param parameters
     * @return
     */
    public static String generatorParamString(Map<String, Object> parameters) {
        StringBuilder params = new StringBuilder();
        if (parameters != null) {
            for (Iterator<String> inter = parameters.keySet().iterator(); inter.hasNext(); ) {
                String name = inter.next();
                String value = parameters.get(name).toString();
                params.append(name).append("=");
                try {
                    params.append(URLEncoder.encode(value, StandardCharsets.UTF_8));
                } catch (Exception e) {
                    String message = String.format(FORMAT_NAME_VALUE, name, value);
                    throw new RuntimeException(message, e);
                }
                if (inter.hasNext()) {
                    params.append("&");
                }
            }
        }
        return params.toString();
    }

    /**
     * 处理url
     *
     * @param rootUrl
     * @param params
     * @return
     */
    public static String handleUrl(String rootUrl, Map<String, Object> params) {
        if (rootUrl.contains(STRING)) {
            return rootUrl;
        }
        if (!Objects.equals(rootUrl.charAt(rootUrl.length() - 1), CHAR) && ObjectUtil.isNotEmpty(params)) {
            rootUrl = rootUrl + STRING;
        }
        return rootUrl;
    }
}

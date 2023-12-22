package com.java.coco.utils.http;

import cn.hutool.core.util.StrUtil;
import com.java.coco.utils.StopWatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * http 封装工具类
 * JDK8 起
 *
 * @author hy
 */
@Slf4j
public class HttpClientUtil {

    public static final int REQUEST_MAX_NUM = 5000;
    public static final int INITIAL_DELAY = 10 * 1000;
    public static final int PERIOD = 5 * 1000;
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String MIME_TYPE = "text/plain";
    private static final int CONNECTION_REQUEST_TIMEOUT = 3000;
    private static final int CONNECT_TIMEOUT = 3000;
    private static final int SOCKET_TIMEOUT = 10000;
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String EMPTY_BODY = "";
    public static final int SO_TIMEOUT = 5000;
    public static final int CORE_POOL_SIZE = 1;
    public static final int IDLE_TIMEOUT = 30;
    private static PoolingHttpClientConnectionManager connectionManager = null;
    private static RequestConfig requestConfig;
    private static final Map<String, Object> EMPTY_PARAMS = new HashMap<>();
    private static final Map<String, String> EMPTY_HEADERS = new HashMap<>();
    private static CloseableHttpClient client;

    /**
     * 请求连接池失败重试策略
     */
    public static class MyRetryHandle implements HttpRequestRetryHandler {
        Logger logger = LoggerFactory.getLogger(MyRetryHandle.class);

        //请求失败时,进行请求重试
        @Override
        public boolean retryRequest(IOException e, int i, HttpContext httpContext) {
            if (i > 3) {
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

    //单例模式创建
    private static void init() {
        synchronized (HttpClientUtil.class) {
            if (client == null) {
                connectionManager = new PoolingHttpClientConnectionManager();
                // http请求线程池，最大连接数
                ConnectionConfig connConfig = ConnectionConfig.custom().setCharset(StandardCharsets.UTF_8).build();
                SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(SO_TIMEOUT).build();
                connectionManager.setDefaultConnectionConfig(connConfig);
                connectionManager.setDefaultSocketConfig(socketConfig);
                // 连接池最大生成连接数
                connectionManager.setMaxTotal(REQUEST_MAX_NUM);
                // 默认设置route最大连接数
                connectionManager.setDefaultMaxPerRoute(REQUEST_MAX_NUM);
                //设置请求参数
                //连接超时时间
                //从线程池中获取线程超时时间
                //设置数据超时时间
                RequestConfig config = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT) //连接超时时间
                    .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT) //从线程池中获取线程超时时间
                    .setSocketTimeout(SOCKET_TIMEOUT) //设置数据超时时间
                    .build();
                // 创建builder
                HttpClientBuilder builder = HttpClients.custom();
                //管理器是共享的，它的生命周期将由调用者管理，并且不会关闭
                //否则可能出现Connection pool shut down异常
                builder.setConnectionManager(connectionManager).setConnectionManagerShared(true);
                // 长连接策略
                builder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
                // 创建httpClient
                client = builder.setDefaultRequestConfig(config).setRetryHandler(new MyRetryHandle()).build();
                // 启动定时器，定时回收过期的连接
                ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(CORE_POOL_SIZE,
                    new BasicThreadFactory.Builder().namingPattern("close-expired-connections-schedule-pool-%d")
                        .daemon(true).build());
                executorService.scheduleAtFixedRate(() -> {
                    // 关闭过期的链接
                    connectionManager.closeExpiredConnections();
                    // 选择关闭 空闲30秒的链接
                    connectionManager.closeIdleConnections(IDLE_TIMEOUT, TimeUnit.SECONDS);
                }, INITIAL_DELAY, PERIOD, TimeUnit.HOURS);

            }
        }
    }

    private static CloseableHttpClient getClientFromPool() {
        if (client == null) {
            init();
        }
        return client;
    }

    /**
     * 发送 GET 请求（HTTP），不带输入数据
     *
     * @param url 请求url
     * @return 返回报文
     */
    public static String doGet(String url) {
        return doGet(url, EMPTY_PARAMS, EMPTY_HEADERS, SOCKET_TIMEOUT);
    }

    public static String doGet(String url, int timeout) {
        return doGet(url, EMPTY_PARAMS, EMPTY_HEADERS, timeout);
    }

    public static String doGetWithParams(String url, Map<String, Object> params) {
        return doGet(url, params, EMPTY_HEADERS, SOCKET_TIMEOUT);
    }

    public static String doGetWithParams(String url, Map<String, Object> params, int timeout) {
        return doGet(url, params, EMPTY_HEADERS, timeout);
    }

    public static String doGetWithHeader(String url, Map<String, String> headers) {
        return doGet(url, EMPTY_PARAMS, headers, SOCKET_TIMEOUT);
    }

    public static String doGetWithHeader(String url, Map<String, String> headers, int timeout) {
        return doGet(url, EMPTY_PARAMS, headers, timeout);
    }

    public static String doPost(String url) {
        return doPost(url, EMPTY_PARAMS, EMPTY_BODY, EMPTY_HEADERS, SOCKET_TIMEOUT);
    }

    public static String doPost(String url, int socketTimeout) {
        return doPost(url, EMPTY_PARAMS, EMPTY_BODY, EMPTY_HEADERS, socketTimeout);
    }

    public static String doPost(String rootUrl, Map<String, Object> params, String body, Map<String, String> headers) {
        return doPost(rootUrl, params, body, headers, SOCKET_TIMEOUT);
    }

    public static String doPostWithBodyAndHeader(String rootUrl, String body, Map<String, String> headers) {
        return doPost(rootUrl, EMPTY_PARAMS, body, headers, SOCKET_TIMEOUT);
    }

    public static String doPostWithParams(String rootUrl, Map<String, Object> params) {
        return doPost(rootUrl, params, EMPTY_BODY, EMPTY_HEADERS, SOCKET_TIMEOUT);
    }

    public static String doPostWithParams(String rootUrl, Map<String, Object> params, int socketTimeout) {
        return doPost(rootUrl, params, EMPTY_BODY, EMPTY_HEADERS, socketTimeout);
    }

    public static String doPostWithHeaders(String url, Map<String, String> headers) {
        return doPost(url, EMPTY_PARAMS, EMPTY_BODY, headers, SOCKET_TIMEOUT);
    }

    public static String doPostWithHeaders(String url, Map<String, String> headers, int socketTimeout) {
        return doPost(url, EMPTY_PARAMS, EMPTY_BODY, headers, socketTimeout);
    }

    public static String doPostWithBody(String url, String body) {
        return doPost(url, EMPTY_PARAMS, body, EMPTY_HEADERS, SOCKET_TIMEOUT);
    }

    public static String doPostWithBody(String url, String body, int socketTimeout) {
        return doPost(url, EMPTY_PARAMS, body, EMPTY_HEADERS, socketTimeout);
    }

    public static String doDelete(String url) {
        return doDelete(url, EMPTY_HEADERS, SOCKET_TIMEOUT);
    }

    public static String doDelete(String url, int socketTimeout) {
        return doDelete(url, EMPTY_HEADERS, socketTimeout);
    }

    public static String doDeleteWithHeaders(String url, Map<String, String> headers) {
        return doDelete(url, headers, SOCKET_TIMEOUT);
    }

    public static String doPutWithHeaders(String url, Map<String, String> headers) {
        return doPut(url, EMPTY_BODY, headers, SOCKET_TIMEOUT);
    }

    public static String doPutWithBodyAndHeaders(String url, Map<String, String> headers, String body) {
        return doPut(url, body, headers, SOCKET_TIMEOUT);
    }

    public static String doPutWithHeaders(String url, Map<String, String> headers, int socketTimeout) {
        return doPut(url, EMPTY_BODY, headers, socketTimeout);
    }

    public static String doPatch(String rootUrl, Map<String, Object> params, String body, Map<String, String> headers) {
        return doPatch(rootUrl, params, body, headers, SOCKET_TIMEOUT);
    }

    public static String doPatchWithParams(String rootUrl, Map<String, Object> params) {
        return doPatch(rootUrl, params, EMPTY_BODY, EMPTY_HEADERS, SOCKET_TIMEOUT);
    }

    public static String doPatchWithBody(String rootUrl, String body) {
        return doPatch(rootUrl, EMPTY_PARAMS, body, EMPTY_HEADERS, SOCKET_TIMEOUT);
    }

    public static String doPatchWithBodyAndHeader(String url, String body, Map<String, String> headers) {
        return doPatch(url, EMPTY_PARAMS, body, headers, SOCKET_TIMEOUT);
    }

    public static String doPostWithForm(String rootUrl, Map<String, Object> params, Map<String, Object> forms,
        Map<String, String> headers, int socketTimeout) {
        StopWatch clock = new StopWatch();
        clock.start();
        String realUrl = rootUrl + generatorParamString(params);
        HttpPost httpPost = new HttpPost(realUrl);
        for (String key : headers.keySet()) {
            httpPost.setHeader(key, headers.get(key));
        }
        if (socketTimeout != SOCKET_TIMEOUT) {
            RequestConfig.Builder configBuilder = RequestConfig.custom();
            // 设置连接超时
            configBuilder.setConnectTimeout(CONNECT_TIMEOUT);
            // 设置读取超时
            configBuilder.setSocketTimeout(socketTimeout);
            // 设置从连接池获取连接实例的超时
            configBuilder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT);
            requestConfig = configBuilder.build();
        }
        httpPost.setConfig(requestConfig);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String response = "";
        if (Objects.nonNull(forms)) {
            //创建 MultipartEntityBuilder,以此来构建我们的参数
            MultipartEntityBuilder EntityBuilder = MultipartEntityBuilder.create();
            //设置字符编码，防止乱码
            ContentType contentType = ContentType.create(MIME_TYPE, StandardCharsets.UTF_8);
            for (Map.Entry<String, Object> entry : forms.entrySet()) {
                if (entry.getValue().getClass().isInstance(EMPTY_BODY)) {
                    //填充我们的文本内容，这里相当于input 框中的 name 与value
                    EntityBuilder.addPart(entry.getKey(), new StringBody(entry.getValue().toString(), contentType));
                } else {
                    //上传我们的文件
                    EntityBuilder.addBinaryBody(entry.getKey(), (File)entry.getValue());
                }
                //参数组装
                httpPost.setEntity(EntityBuilder.build());
            }
        }
        try (CloseableHttpResponse responseDto = httpclient.execute(httpPost)) {
            clock.stop();
            response = EntityUtils.toString(responseDto.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("请求异常：", e);
        }
        log.info("url:{},costs:{}ms, response: {}", realUrl, clock.getTotalTimeMillis(), response);
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
        StopWatch clock = new StopWatch();
        clock.start();
        String realUrl = rootUrl + generatorParamString(params);
        HttpPost httpPost = new HttpPost(realUrl);
        for (String key : headers.keySet()) {
            httpPost.setHeader(key, headers.get(key));
        }
        if (socketTimeout != SOCKET_TIMEOUT) {
            RequestConfig.Builder configBuilder = RequestConfig.custom();
            // 设置连接超时
            configBuilder.setConnectTimeout(CONNECT_TIMEOUT);
            // 设置读取超时
            configBuilder.setSocketTimeout(socketTimeout);
            // 设置从连接池获取连接实例的超时
            configBuilder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT);
            requestConfig = configBuilder.build();
        }
        httpPost.setConfig(requestConfig);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String response = EMPTY_BODY;
        if (StrUtil.isNotEmpty(body)) {
            StringEntity stringEntity = new StringEntity(body, StandardCharsets.UTF_8);
            stringEntity.setContentEncoding(DEFAULT_CHARSET);
            stringEntity.setContentType(CONTENT_TYPE_JSON);
            httpPost.setEntity(stringEntity);
        }
        try (CloseableHttpResponse responseDto = httpclient.execute(httpPost)) {
            clock.stop();
            response = EntityUtils.toString(responseDto.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("请求异常：", e);
        }
        log.info("url:{},costs:{}ms, response: {}", realUrl, clock.getTotalTimeMillis(), response);
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
        StopWatch clock = new StopWatch();
        clock.start();
        String realUrl = rootUrl + generatorParamString(params);
        HttpGet httpGet = new HttpGet(realUrl);
        for (String key : headers.keySet()) {
            httpGet.setHeader(key, headers.get(key));
        }
        if (socketTimeout != SOCKET_TIMEOUT) {
            RequestConfig.Builder configBuilder = RequestConfig.custom();
            // 设置连接超时
            configBuilder.setConnectTimeout(CONNECT_TIMEOUT);
            // 设置读取超时
            configBuilder.setSocketTimeout(socketTimeout);
            // 设置从连接池获取连接实例的超时
            configBuilder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT);
            requestConfig = configBuilder.build();
        }
        httpGet.setConfig(requestConfig);
        CloseableHttpClient httpclient = getClientFromPool();
        String response = EMPTY_BODY;
        try (CloseableHttpResponse responseDto = httpclient.execute(httpGet)) {
            clock.stop();
            response = EntityUtils.toString(responseDto.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("请求异常：", e);
        }
        log.info("url:{},costs:{}ms, response: {}", realUrl, clock.getTotalTimeMillis(), response);
        return response;
    }

    /**
     * delete请求
     *
     * @param url
     * @param headers
     * @param socketTimeout
     * @return
     */
    public static String doDelete(String url, Map<String, String> headers, int socketTimeout) {
        StopWatch clock = new StopWatch();
        clock.start();
        HttpDelete httpDelete = new HttpDelete(url);
        for (String key : headers.keySet()) {
            httpDelete.setHeader(key, headers.get(key));
        }
        if (socketTimeout != SOCKET_TIMEOUT) {
            RequestConfig.Builder configBuilder = RequestConfig.custom();
            // 设置连接超时
            configBuilder.setConnectTimeout(CONNECT_TIMEOUT);
            // 设置读取超时
            configBuilder.setSocketTimeout(socketTimeout);
            // 设置从连接池获取连接实例的超时
            configBuilder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT);
            requestConfig = configBuilder.build();
        }
        httpDelete.setConfig(requestConfig);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String response = EMPTY_BODY;
        try (CloseableHttpResponse responseDto = httpclient.execute(httpDelete)) {
            clock.stop();
            response = EntityUtils.toString(responseDto.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("请求异常：", e);
        }
        log.info("url:{},costs:{}ms, response: {}", url, clock.getTotalTimeMillis(), response);
        return response;
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
        StopWatch clock = new StopWatch();
        clock.start();
        HttpPut httpPut = new HttpPut(url);
        for (String key : headers.keySet()) {
            httpPut.setHeader(key, headers.get(key));
        }
        if (socketTimeout != SOCKET_TIMEOUT) {
            RequestConfig.Builder configBuilder = RequestConfig.custom();
            // 设置连接超时
            configBuilder.setConnectTimeout(CONNECT_TIMEOUT);
            // 设置读取超时
            configBuilder.setSocketTimeout(socketTimeout);
            // 设置从连接池获取连接实例的超时
            configBuilder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT);
            requestConfig = configBuilder.build();
        }
        httpPut.setConfig(requestConfig);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String response = EMPTY_BODY;
        if (StrUtil.isNotEmpty(body)) {
            StringEntity stringEntity = new StringEntity(body, StandardCharsets.UTF_8);
            stringEntity.setContentEncoding(DEFAULT_CHARSET);
            stringEntity.setContentType(CONTENT_TYPE_JSON);
            httpPut.setEntity(stringEntity);
        }
        try (CloseableHttpResponse responseDto = httpclient.execute(httpPut)) {
            clock.stop();
            response = EntityUtils.toString(responseDto.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("请求异常：", e);
        }
        log.info("url:{},costs:{}ms, response: {}", url, clock.getTotalTimeMillis(), response);
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
        StopWatch clock = new StopWatch();
        clock.start();
        String realUrl = rootUrl + generatorParamString(params);
        HttpPatch httpPatch = new HttpPatch(realUrl);
        for (String key : headers.keySet()) {
            httpPatch.setHeader(key, headers.get(key));
        }
        if (socketTimeout != SOCKET_TIMEOUT) {
            RequestConfig.Builder configBuilder = RequestConfig.custom();
            // 设置连接超时
            configBuilder.setConnectTimeout(CONNECT_TIMEOUT);
            // 设置读取超时
            configBuilder.setSocketTimeout(socketTimeout);
            // 设置从连接池获取连接实例的超时
            configBuilder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT);
            requestConfig = configBuilder.build();
        }
        httpPatch.setConfig(requestConfig);
        String response = EMPTY_BODY;
        try(CloseableHttpClient httpclient = HttpClients.createDefault())  {
            if (StrUtil.isNotEmpty(body)) {
                StringEntity stringEntity = new StringEntity(body, StandardCharsets.UTF_8);
                stringEntity.setContentEncoding(DEFAULT_CHARSET);
                stringEntity.setContentType(CONTENT_TYPE_JSON);
                httpPatch.setEntity(stringEntity);
            }

            clock.stop();
            try (CloseableHttpResponse responseDto = httpclient.execute(httpPatch)) {
                clock.stop();
                response = EntityUtils.toString(responseDto.getEntity(), StandardCharsets.UTF_8);
            } catch (Exception e) {
                log.error("请求异常：", e);
            }

        }catch (IOException ioException){
            log.error("请求异常：", ioException);
        }


        log.info("url:{},costs:{}ms, response: {}", realUrl, clock.getTotalTimeMillis(), response);
        return response;
    }

    public static String generatorParamString(Map<String, Object> parameters) {
        StringBuilder params = new StringBuilder();
        if (parameters != null) {
            for (Iterator<String> iter = parameters.keySet().iterator(); iter.hasNext(); ) {
                String name = iter.next();
                String value = parameters.get(name).toString();
                params.append(name).append("=");
                try {
                    params.append(URLEncoder.encode(value, DEFAULT_CHARSET));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e.getMessage(), e);
                } catch (Exception e) {
                    String message = String.format("'%s'='%s'", name, value);
                    throw new RuntimeException(message, e);
                }
                if (iter.hasNext()) {
                    params.append("&");
                }
            }
        }
        return params.toString();
    }

}

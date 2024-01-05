package com.java.coco.utils.http;

import java.util.HashMap;
import java.util.Map;

/**
 * @author meta
 */
public class HttpUtils {

    /**
     * 设置数据超时时间
     */
    private static final int SOCKET_TIMEOUT = 10000;
    private static final String EMPTY_BODY = "";

    private static final Map<String, Object> EMPTY_PARAMS = new HashMap<>();
    private static final Map<String, String> EMPTY_HEADERS = new HashMap<>();

    /**
     * 发送 GET 请求（HTTP），不带输入数据
     *
     * @param url 请求url
     * @return 返回报文
     */
    public static String doGet(String url) {
        return HttpClientUtil.doGet(url, EMPTY_PARAMS, EMPTY_HEADERS, SOCKET_TIMEOUT);
    }

    public static String doGet(String url, int timeout) {
        return HttpClientUtil.doGet(url, EMPTY_PARAMS, EMPTY_HEADERS, timeout);
    }

    public static String doGetWithParams(String url, Map<String, Object> params) {
        return HttpClientUtil.doGet(url, params, EMPTY_HEADERS, SOCKET_TIMEOUT);
    }

    public static String doGetWithParams(String url, Map<String, Object> params, int timeout) {
        return HttpClientUtil.doGet(url, params, EMPTY_HEADERS, timeout);
    }

    public static String doGetWithHeader(String url, Map<String, String> headers) {
        return HttpClientUtil.doGet(url, EMPTY_PARAMS, headers, SOCKET_TIMEOUT);
    }

    public static String doGetWithHeader(String url, Map<String, String> headers, int timeout) {
        return HttpClientUtil.doGet(url, EMPTY_PARAMS, headers, timeout);
    }

    public static String doPost(String url) {
        return HttpClientUtil.doPost(url, EMPTY_PARAMS, EMPTY_BODY, EMPTY_HEADERS, SOCKET_TIMEOUT);
    }

    public static String doPost(String url, int socketTimeout) {
        return HttpClientUtil.doPost(url, EMPTY_PARAMS, EMPTY_BODY, EMPTY_HEADERS, socketTimeout);
    }

    public static String doPost(String rootUrl, Map<String, Object> params, String body, Map<String, String> headers) {
        return HttpClientUtil.doPost(rootUrl, params, body, headers, SOCKET_TIMEOUT);
    }

    public static String doPostWithBodyAndHeader(String rootUrl, String body, Map<String, String> headers) {
        return HttpClientUtil.doPost(rootUrl, EMPTY_PARAMS, body, headers, SOCKET_TIMEOUT);
    }

    public static String doPostWithParams(String rootUrl, Map<String, Object> params) {
        return HttpClientUtil.doPost(rootUrl, params, EMPTY_BODY, EMPTY_HEADERS, SOCKET_TIMEOUT);
    }

    public static String doPostWithParams(String rootUrl, Map<String, Object> params, int socketTimeout) {
        return HttpClientUtil.doPost(rootUrl, params, EMPTY_BODY, EMPTY_HEADERS, socketTimeout);
    }

    public static String doPostWithHeaders(String url, Map<String, String> headers) {
        return HttpClientUtil.doPost(url, EMPTY_PARAMS, EMPTY_BODY, headers, SOCKET_TIMEOUT);
    }

    public static String doPostWithHeaders(String url, Map<String, String> headers, int socketTimeout) {
        return HttpClientUtil.doPost(url, EMPTY_PARAMS, EMPTY_BODY, headers, socketTimeout);
    }

    public static String doPostWithBody(String url, String body) {
        return HttpClientUtil.doPost(url, EMPTY_PARAMS, body, EMPTY_HEADERS, SOCKET_TIMEOUT);
    }

    public static String doPostWithBody(String url, String body, int socketTimeout) {
        return HttpClientUtil.doPost(url, EMPTY_PARAMS, body, EMPTY_HEADERS, socketTimeout);
    }

    public static String doDelete(String url) {
        return HttpClientUtil.doDelete(url, EMPTY_HEADERS, SOCKET_TIMEOUT);
    }

    public static String doDelete(String url, int socketTimeout) {
        return HttpClientUtil.doDelete(url, EMPTY_HEADERS, socketTimeout);
    }

    public static String doDeleteWithHeaders(String url, Map<String, String> headers) {
        return HttpClientUtil.doDelete(url, headers, SOCKET_TIMEOUT);
    }

    public static String doPutWithHeaders(String url, Map<String, String> headers) {
        return HttpClientUtil.doPut(url, EMPTY_BODY, headers, SOCKET_TIMEOUT);
    }

    public static String doPutWithBodyAndHeaders(String url, Map<String, String> headers, String body) {
        return HttpClientUtil.doPut(url, body, headers, SOCKET_TIMEOUT);
    }

    public static String doPutWithHeaders(String url, Map<String, String> headers, int socketTimeout) {
        return HttpClientUtil.doPut(url, EMPTY_BODY, headers, socketTimeout);
    }

    public static String doPatch(String rootUrl, Map<String, Object> params, String body, Map<String, String> headers) {
        return HttpClientUtil.doPatch(rootUrl, params, body, headers, SOCKET_TIMEOUT);
    }

    public static String doPatchWithParams(String rootUrl, Map<String, Object> params) {
        return HttpClientUtil.doPatch(rootUrl, params, EMPTY_BODY, EMPTY_HEADERS, SOCKET_TIMEOUT);
    }

    public static String doPatchWithBody(String rootUrl, String body) {
        return HttpClientUtil.doPatch(rootUrl, EMPTY_PARAMS, body, EMPTY_HEADERS, SOCKET_TIMEOUT);
    }

    public static String doPatchWithBodyAndHeader(String url, String body, Map<String, String> headers) {
        return HttpClientUtil.doPatch(url, EMPTY_PARAMS, body, headers, SOCKET_TIMEOUT);
    }
}

package com.java.tutorial.project.util;


import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.UUID;

/**
 * @author meta
 */
@Slf4j
public class TraceIDUtil {

    public static final String DEFAULT_TRACE_ID = "traceId";

    private static final char[] HEX = "0123456789abcdef".toCharArray();

    /**
     * 根据入参设置traceId到MDC
     *
     * @param traceId
     */
    private static void setTraceIdToMdc(String traceId) {
        MDC.put(DEFAULT_TRACE_ID, traceId);
    }

    /**
     * 根据TTL或MDC中的TraceID进行设置 1, TTL和MDC都不存在TraceID,生成TraceID并设置到TTL和MDC当中 2, 如果MDC中有但是TTL中没有,将MDC中的TraceID设置到TTL 3,
     * 如果TTL中有但是MDC中没有,将TTL中的TraceID设置到TTL
     *
     * @return traceId
     */
    public static String getTraceId() {
        String traceId = MDC.get(DEFAULT_TRACE_ID);
        if (null == traceId) {
            traceId = createTraceId();
            setTraceIdToMdc(traceId);
        }
        return traceId;
    }

    /**
     * 清除MDC和TTL中的traceID
     */
    public static void clearTraceId() {
        MDC.remove(DEFAULT_TRACE_ID);
    }

    /**
     * 服务器 IP + ID 产生的时间 + 自增序列 + 当前进程号
     * <p>
     * c0a831c41614164456128e08419836
     * <p>
     * 前 8 位 c0a831c4 即产生 TraceId 的机器的 IP，这是一个十六进制的数字，每两位代表 IP 中的一段， 我们把这个数字，按每两位转成 10 进制即可得到常见的 IP 地址表示方式
     * 192.168.49.196，可以根据这个规律来查找到请求经过的第一个服务器。
     * <p>
     * 后面的 13 位 1403169275002 是产生 TraceId 的时间
     * <p>
     * 之后的 4 位 e084 是一个随机序列。
     * <p>
     * 最后的 5 位 19836 是当前的进程 ID，为了防止单机多进程出现 TraceId 冲突的情况。
     *
     * @return
     */
    public static String createTraceId() {
        return IdUtil.fastSimpleUUID() + System.currentTimeMillis() + getRandomString() + getProcessId();
    }

    private static String ipToLong(String ipString) {
        Long[] ip = new Long[4];
        int pos1 = ipString.indexOf(".");
        int pos2 = ipString.indexOf(".", pos1 + 1);
        int pos3 = ipString.indexOf(".", pos2 + 1);
        ip[0] = Long.parseLong(ipString.substring(0, pos1));
        ip[1] = Long.parseLong(ipString.substring(pos1 + 1, pos2));
        ip[2] = Long.parseLong(ipString.substring(pos2 + 1, pos3));
        ip[3] = Long.parseLong(ipString.substring(pos3 + 1));
        return toHex(ip[0].intValue()) + toHex(ip[1].intValue()) + toHex(ip[2].intValue()) + toHex(ip[3].intValue());
    }

    private static String getProcessId() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        return runtimeMXBean.getName().split("@")[0];
    }

    private static String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder ans = new StringBuilder();
        while (num != 0) {
            int temp = num & 0xf;
            ans.append(HEX[temp]);
            num >>>= 4;
        }
        return ans.reverse().toString();
    }

    private static String getRandomString() {
        return UUID.randomUUID().toString().substring(0, 4);
    }

}
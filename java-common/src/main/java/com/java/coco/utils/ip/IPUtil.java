package com.java.coco.utils.ip;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

@Slf4j
public class IPUtil {

    /**
     * 获取本地的ip地址
     */
    public static String getLocalIPAddress() {
        try {
            Enumeration<NetworkInterface> b = NetworkInterface.getNetworkInterfaces();
            while (b.hasMoreElements()) {
                for (InterfaceAddress f : b.nextElement().getInterfaceAddresses()) {
                    if (f.getAddress().isSiteLocalAddress()) {
                        return f.getAddress().getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            log.error(e.getMessage());
        }
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error(e.getMessage());
            return "--";
        }
    }

    /**
     * 过滤本地的ip地址
     */
    public static boolean checkIPAddress(String ip, String ipList) {
        String[] ipArray = ipList.split(",");
        for (String s : ipArray) {
            if (ip.equals(s)) {
                return true;
            }
        }
        return false;
    }
}

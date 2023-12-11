package com.java.tutorial.project.common.constant;

public class SignalMessageConstant {
    public static final String title = "start_up_signal";
    public static String content1 =
        "[{\"text\":\"douyin_number_in_the_live_broadcast_room: %s\"},{\"text\":\"If the push data is abnormal, check whether the host page is dormant and time %s\"}]";

    public static String content2 =
        "[{\"text\":\"douyin_number_in_the_live_broadcast_room: %s\"},{\"text\":\"The client did not create a persistent connection to the server, time %s\"}]";

    public static String content3 =
        "[{\"text\":\"douyin_number_in_the_live_broadcast_room: %s\"},{\"text\":\"The communication time between the host page and the server has expired and has been automatically disconnected %s\"}]";

    public static String content4 =
        "[{\"text\":\"douyin_number_in_the_live_broadcast_room: %s\"},{\"text\":\"The host page is closed and actively disconnected from the server, time %s\"}]";
}

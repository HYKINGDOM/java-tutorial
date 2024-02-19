package com.java.tutorial.project.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnumCacheTest {

    private static final OrderType DEF = OrderType._00;
    private static final int TIMES = 10000000;

    static void compare(String code) {
        long s = System.currentTimeMillis();
        for (int idx = 0; idx < TIMES; idx++) {
            OrderType.getEnumByCode(code, DEF);
        }
        long t = System.currentTimeMillis() - s;
        System.out.println(String.format("枚举->%s : %s", code, t));

        s = System.currentTimeMillis();
        for (int idx = 0; idx < TIMES; idx++) {
            EnumCache.findByValue(OrderType.class, code, DEF);
        }
        t = System.currentTimeMillis() - s;
        System.out.println(String.format("缓存->%s : %s", code, t));
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        for (int idx = 0; idx < 2; idx++) {
            compare("NotExist");
            for (OrderType value : OrderType.values()) {
                compare(value.getCode());
            }
            System.out.println("=================");
        }
    }

    @Test
    public void test_enum_demo_01() {
        System.out.println(EnumCache.findByName(StatusEnum.class, "SUCCESS", null));
        // 返回默认值StatusEnum.INIT
        System.out.println(EnumCache.findByName(StatusEnum.class, null, StatusEnum.INIT));
        // 返回默认值StatusEnum.INIT
        System.out.println(EnumCache.findByName(StatusEnum.class, "ERROR", StatusEnum.INIT));

        System.out.println(EnumCache.findByValue(StatusEnum.class, "S", null));
        // 返回默认值StatusEnum.INIT
        System.out.println(EnumCache.findByValue(StatusEnum.class, null, StatusEnum.INIT));
        // 返回默认值StatusEnum.INIT
        System.out.println(EnumCache.findByValue(StatusEnum.class, "ERROR", StatusEnum.INIT));
    }

    enum OrderType {
        _00("00", "00"), _01("01", "01"), _02("02", "02"), _03("03", "03"), _04("04", "04"), _05("05", "05"),
        _06("06", "06"), _07("07", "07"), _08("08", "08"), _09("09", "09"), _10("10", "10");
        static {
            EnumCache.registerByValue(OrderType.class, OrderType.values(), OrderType::getCode);
        }

        private String code;
        private String desc;

        OrderType(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static OrderType getEnumByCode(String code, OrderType def) {
            OrderType[] values = OrderType.values();
            for (OrderType value : values) {
                if (value.getCode().equals(code)) {
                    return value;
                }
            }
            return def;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }
}
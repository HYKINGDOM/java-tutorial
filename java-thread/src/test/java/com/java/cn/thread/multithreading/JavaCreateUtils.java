package com.java.util.javautil.scs.Multithreading;


import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

public class JavaCreateUtils {


    @Test
    public void test_create_uuid_util(){
        System.out.println(Arrays.toString(getUUID(6)));
    }

    //得到指定数量的UUID，以数组的形式返回
    public static String[] getUUID(int num){

        if( num <= 0)
            return null;

        String[] uuidArr = new String[num];

        for (int i = 0; i < uuidArr.length; i++) {
            uuidArr[i] = getUUID32();
        }

        return uuidArr;
    }

    public static String getUUID32(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }


    /**
     * 随机生成UUID
     * @return
     */
    public static synchronized String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString();
        return str.replace("-", "");
    }
    /**
     * 根据字符串生成固定UUID
     * @param name
     * @return
     */
    public static synchronized String getUUID(String name){
        UUID uuid=UUID.nameUUIDFromBytes(name.getBytes());
        String str = uuid.toString();
        return str.replace("-", "");
    }


}

package com.java.util.javautil.jvm;

/**
 * 类加载器
 */
public class ClassloaderTest {


    public static void main(String[] args) {

        //获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        //获取其上层 扩展类加载器
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);

        //获取其上层: 引导类加载器 使用c/c++实现 嵌套在JVM内部
        ClassLoader classLoader = parent.getParent();
        System.out.println(classLoader);

        //获取自定义类加载器: 默认使用系统类加载器进行加载
        ClassLoader classLoader1 = ClassloaderTest.class.getClassLoader();
        System.out.println(classLoader1);

        //String类使用引导类加载器进行加载--->java的核心类库都是使用引导类加载器进行加载
        ClassLoader classLoader2 = String.class.getClassLoader();
        System.out.println(classLoader2);
    }
}

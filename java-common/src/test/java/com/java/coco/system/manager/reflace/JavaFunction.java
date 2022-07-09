package com.java.coco.system.manager.reflace;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JavaFunction {


    @Test
    public void test_java_class() {
        String className = "src.project.scs.manager.reflace.Hero";

        try {
            //获取类对象
            Class pClass1 = Class.forName(className);
            //获取构造器对象
            Class pClass2 = Hero.class;
            //获取对象
            Class pClass3 = new Hero().getClass();

            System.out.println(pClass1 == pClass2);
            System.out.println(pClass1 == pClass3);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /*
	 * 通过Class对象可以获取某个类中的：构造方法、成员变量、成员方法；并访问成员；
	 *
	 * 1.获取构造方法：
	 * 		1).批量的方法：
	 * 			public Constructor[] getConstructors()：所有"公有的"构造方法
	            public Constructor[] getDeclaredConstructors()：获取所有的构造方法(包括私有、受保护、默认、公有)

	 * 		2).获取单个的方法，并调用：
	 * 			public Constructor getConstructor(Class... parameterTypes):获取单个的"公有的"构造方法：
	 * 			public Constructor getDeclaredConstructor(Class... parameterTypes):获取"某个构造方法"可以是私有的，或受保护、默认、公有；
	 *
	 * 2.创建对象
	 * 		Constructor对象调用newInstance(Object... initargs)
	 */
    @Test
    public void test_contractor_java_class() {

        try {
            //1.加载Class对象
            Class clazz = Hero.class;


            //2.获取所有公有构造方法
            System.out.println("**********************所有公有构造方法*********************************");
            Constructor[] conArray = clazz.getConstructors();
            for (Constructor c : conArray) {
                System.out.println(c);
            }

            System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
            conArray = clazz.getDeclaredConstructors();
            for (Constructor c : conArray) {
                System.out.println(c);
            }

            System.out.println("*****************获取公有、无参的构造方法*******************************");
            Constructor con = clazz.getConstructor(null);
            //1>、因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
            //2>、返回的是描述这个无参构造函数的类对象。
            System.out.println("con = " + con);
            //调用构造方法
            Object obj = con.newInstance();


            System.out.println("******************获取私有构造方法，并调用*******************************");
            con = clazz.getDeclaredConstructor(float.class);
            System.out.println(con);
            //调用构造方法
            con.setAccessible(true);//暴力访问(忽略掉访问修饰符)
            obj = con.newInstance(100);

            System.out.println("******************获取私有方法，并调用*******************************");
            Method method = clazz.getDeclaredMethod("sumHero", int.class);
            System.out.println(method);

        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取成员变量并 使用
     */
    @Test
    public void java_test_hero_plus_reflace() {
        HeroPlus h = new HeroPlus();
        //使用传统方式修改name的值为garen
        h.name = "garen";

        try {
            //获取类HeroPlus的名字叫做name的字段
            Field f1 = h.getClass().getDeclaredField("name");
            //修改这个字段的值
            f1.set(h, "teemo");
            //打印被修改后的值
            System.out.println(h.name);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取HeroPlus类的对象 h
     * 获取成员方法：
     * public Method getMethod(String name ，Class<?>… parameterTypes):获取"公有方法"；（包含了父类的方法也包含Object类）
     * public Method getDeclaredMethods(String name ，Class<?>… parameterTypes) :获取成员方法，包括私有的(不包括继承的)
     * 参数解释：
     *   name : 方法名；
     *   Class … : 形参的Class类型对象
     * 调用方法
     * Method --> public Object invoke(Object obj,Object… args):
     * 参数说明：
     *   obj : 要调用方法的对象；
     *   args:调用方式时所传递的实参；
     */
    @Test
    public void test_java_reflex_method(){
        HeroPlus heroPlus = new HeroPlus();

        Class<HeroPlus> heroPlusClass = HeroPlus.class;

        try {
            Method method = heroPlusClass.getMethod("setName",String.class);
            method.invoke(heroPlus,"aike");
            System.out.println(heroPlus.getName());

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_java_reflex_main_method(){
        try {
            //1、获取HeroPlus对象的字节码
            Class clazz = HeroPlus.class;

            //2、获取main方法,第一个参数：方法名称，第二个参数：方法形参的类型，
            Method methodMain = clazz.getMethod("main", String[].class);
            //3、调用main方法
            // methodMain.invoke(null, new String[]{"a","b","c"});
            //第一个参数，对象类型，因为方法是static静态的，所以为null可以，第二个参数是String数组，这里要注意在jdk1.4时是数组，jdk1.5之后是可变参数
            //这里拆的时候将  new String[]{"a","b","c"} 拆成3个对象。所以需要将它强转。
            methodMain.invoke(null, (Object)new String[]{"a","b","c"});//方式一
            // methodMain.invoke(null, new Object[]{new String[]{"a","b","c"}});//方式二

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.java.util.javautil.system.manager.reflace;

public class Hero {
    public String name; //姓名
    public float hp; //血量
    public float armor; //护甲
    public int moveSpeed; //移动速度


    //---------------构造方法-------------------
    //（默认的构造方法）
    Hero(String str) {
        System.out.println("(默认)的构造方法 s = " + str);
    }

    //无参构造方法
    public Hero() {
        System.out.println("调用了公有、无参构造方法执行了。。。");
    }

    //有一个参数的构造方法
    public Hero(char name) {
        System.out.println("姓名：" + name);
    }

    //有多个参数的构造方法
    public Hero(String name, float hp) {
        System.out.println("姓名：" + name + "血量：" + hp);
    }

    //受保护的构造方法
    protected Hero(boolean n) {
        System.out.println("受保护的构造方法 n = " + n);
    }

    //私有构造方法
    private Hero(float hp) {
        System.out.println("私有的构造方法   血量：" + hp);
    }


    public int sumHero(int num) {
        return num;
    }

    private int sumHero(int num, int data) {
        return num + data;
    }


}

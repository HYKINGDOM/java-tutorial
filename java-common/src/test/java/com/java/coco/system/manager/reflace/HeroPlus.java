package com.java.coco.system.manager.reflace;

import java.util.Arrays;

public class HeroPlus {
    public String name;
    public float hp;
    public int damage;
    public int id;

    public HeroPlus() {

    }

    public HeroPlus(String string) {
        name = string;
    }

    public static void main(String[] args) {
        System.out.println("开始执行main方法" + Arrays.toString(args));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hero [name=" + name + "]";
    }

    public boolean isDead() {
        return false;
    }

    public void attackHero(HeroPlus h2) {
        System.out.println(this.name + " 正在攻击 " + h2.getName());
    }
}


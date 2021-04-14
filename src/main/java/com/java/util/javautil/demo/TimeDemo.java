package com.java.util.javautil.demo;

public class TimeDemo {


    public static void main(String[] args) {
        int date1 = 29700;
        String change = change(date1);
        System.out.println(change);


    }


    public static String change(int second){
        int h = 0;
        int d = 0;
        int s = 0;
        int temp = second%3600;
        if(second>3600){
            h= second/3600;
            if(temp!=0){
                if(temp>60){
                    d = temp/60;
                    if(temp%60!=0){
                        s = temp%60;
                    }
                }else{
                    s = temp;
                }
            }
        }else{
            d = second/60;
            if(second%60!=0){
                s = second%60;
            }
        }

        return h+"时"+d+"分"+s+"秒";
    }
}

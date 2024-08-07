package com.crinnogao.jingyu.javabase;

public class OverloadDemo {
    public static void main(String[] args) {

    }


    public void getMouValue() {
        System.out.println("我是第一个！");
    }

    public String getMouValue(String a) {
        System.out.println("我是第一个！");
        return a;
    }
}

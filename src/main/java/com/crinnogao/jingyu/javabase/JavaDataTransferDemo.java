package com.crinnogao.jingyu.javabase;

public class JavaDataTransferDemo {

    public static void main(String[] args) {
        String str = new String("abf");
        getChangeStr(str);
        System.out.println(str);
    }

    static void getChangeStr(String str) {
        str = new String("678");
    }

}

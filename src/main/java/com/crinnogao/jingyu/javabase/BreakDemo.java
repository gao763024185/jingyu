package com.crinnogao.jingyu.javabase;

public class BreakDemo {
    public static void main(String[] args) {
        boolean a = true;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 5) {
                    break;
                }
                System.out.println("i == " + i + " j == " + j);
            }
        }
    }
}

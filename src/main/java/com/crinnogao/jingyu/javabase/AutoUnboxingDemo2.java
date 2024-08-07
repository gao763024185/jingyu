package com.crinnogao.jingyu.javabase;

public class AutoUnboxingDemo2 {
    public static void main(String[] args) {
        Integer
                f1 = 100,
                f2 = 100,
                f3 = 150,
                f4 = 150;

        System.out.println(f1 == f2);  // true java默认给Integer对象缓存范围是-128——127
        System.out.println(f3 == f4); // false 150 超过缓存范围 会创建新的对象  两者引用不同
    }
}

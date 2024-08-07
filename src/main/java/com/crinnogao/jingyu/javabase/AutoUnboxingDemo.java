package com.crinnogao.jingyu.javabase;

public class AutoUnboxingDemo {
    public static void main(String[] args) {
        Integer s1 = new Integer(3);

        Integer s2 = new Integer(3);

        Integer s3 = s1;

        System.out.println(s1 == s2); // false  s1、s2表示创建了新的对象 不同的引用
        System.out.println(s1.equals(s2)); // true s1和s2 内容相同

        System.out.println(s1 == s3); // true s1 s3指向了相同的引用
        System.out.println(s1.equals(s3)); // true s1和s3指向了同一个引用对象

        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = new Integer(100);

        System.out.println(i1 == i2); // true 缓存了相同的对象
        System.out.println(i1.equals(i2)); // true 内容相同

        System.out.println(i1 == i3); // false  i3通过new创建的 不会使用缓存
        System.out.println(i1.equals(i3)); // true
    }
}

package com.crinnogao.jingyu.concurrent.single;

/**
 * 单例模式--懒汉模式  第一次使用时创建实例
 * 多线程下 不加同步控制 会创建多个实例 线程不安全
 */
public class SingletonLazyUnsafe {
    // 延迟加载
    private static SingletonLazyUnsafe instance;

    // 私有构造函数防止外部私有化
    private SingletonLazyUnsafe() {
    }

    ;


    // 提供全局的静态方法获取实例 未加同步控制 线程不安全
    public static SingletonLazyUnsafe getInstance() {
        if (instance == null) {
            instance = new SingletonLazyUnsafe();
        }
        return instance;
    }
}

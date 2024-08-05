package com.crinnogao.jingyu.concurrent.single;

/**
 * 单例模式--懒汉模式  双重检查锁定 线程安全
 * 代码块加上 synchronized 实现线程安全
 */
public class SingletonLazyDoubleCheck {
    // 延迟加载 使用volatile 关键字防止指令重排
    private static volatile SingletonLazyDoubleCheck instance;

    // 私有构造函数防止外部私有化
    private SingletonLazyDoubleCheck() {
    }

    ;


    // 提供全局的静态方法获取实例 未加同步控制 线程不安全
    public static SingletonLazyDoubleCheck getInstance() {
        // 第一次检查
        if (instance == null) {
            // 同步块 只有当instance为null时 才进入
            synchronized (SingletonLazyDoubleCheck.class) {
                // 第二次检查 防止多个线程同时进入
                if (instance == null) {
                    instance = new SingletonLazyDoubleCheck();
                }
            }
        }
        return instance;
    }
}

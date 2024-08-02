package com.crinnogao.jingyu.concurrent.single;

/**
 * 单例模式--懒汉模式  线程安全 方法同步
 * 代码块加上 synchronized 实现线程安全
 */
public class SingletonLazySafeSync {
    // 延迟加载
    private static SingletonLazySafeSync instance;

    // 私有构造函数防止外部私有化
    private SingletonLazySafeSync() {
    }

    ;


    // 提供全局的静态方法获取实例 未加同步控制 线程不安全
    public static synchronized SingletonLazySafeSync getInstance() {
        if (instance == null) {
            instance = new SingletonLazySafeSync();
        }
        return instance;
    }
}

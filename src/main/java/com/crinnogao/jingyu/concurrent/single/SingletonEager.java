package com.crinnogao.jingyu.concurrent.single;

/**
 * 单例模式--饿汉式 在类加载时就完成了初始化 所以是线程安全的
 */
public class SingletonEager {

    // 在类加载时就完成了初始化 所以类加载较慢 但获取对象速度快
    private static final SingletonEager instance = new SingletonEager();

    // 私有构造函数 防止外部实例化
    private SingletonEager() {
    }

    ;

    // 提供全局静态方法获取实例
    private SingletonEager getInstance() {
        return instance;
    }
}

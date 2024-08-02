package com.crinnogao.jingyu.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    private final Map<Integer, Object> cache = new HashMap<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public Lock getWriteLock() {
        return readWriteLock.writeLock();
    }

    public Lock getReadLock() {
        return readWriteLock.readLock();
    }


    // 读缓存的方法
    private Object get(Integer key) {
        getReadLock().lock();
        try {
            return cache.get(key);
        } finally {
            getReadLock().unlock();
        }
    }

    // 写缓存方法
    private Object put(Integer key, Object value) {
        getWriteLock().lock();
        try {
            cache.put(key, value);
            return "data put sus";
        } finally {
            getWriteLock().unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockDemo lockDemo = new ReadWriteLockDemo();
        // 创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                30,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new ThreadPoolExecutor.AbortPolicy()
        );

        // 启用多个线程写操作 5个任务
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            threadPoolExecutor.submit(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " 开始写任务--》写入缓存数据： " + lockDemo.put(finalI, "hahahha"+finalI));
            });
        }


        // 启用多个线程读操作 10个读任务
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPoolExecutor.submit(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " 开始读任务--》读取缓存数据： " + lockDemo.get(finalI));
            });
        }

        threadPoolExecutor.shutdown();
    }

}

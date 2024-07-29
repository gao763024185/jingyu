package com.crinnogao.jingyu.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors.newCachedThreadPool() 创建一个可缓存的线程池
 * 阻塞队列：SynchronousQueue
 */
public class NewCacheThreadPoolDemo {

    public static void main(String[] args) {
        // 创建一个可缓存的线程池 corePoolSize是0 最大线程数是 Integer.MAX_VALUE 相当于没有设置界限
        ExecutorService executorService = Executors.newCachedThreadPool();
        /*
         *    public static ExecutorService newCachedThreadPool() {
         *         return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
         *                 60L, TimeUnit.SECONDS,
         *                 new SynchronousQueue<Runnable>());
         *     }
         *
         *     SynchronousQueue：同步队列 一个不存储元素的阻塞队列
         */
        //提交任务到线程池
        for (int i = 0; i < 10; i++) {
            int taskId = i;
            executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " 开始执行任务 " + taskId);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " 完成任务 " + taskId);
            });
        }
        // 关闭线程池（不接受新任务） 等待任务完成
        executorService.shutdown();

        // 等待所有任务完成
        while (!executorService.isTerminated()) {
//            System.out.println("有任务未完成！请等待~~~");
        }

        System.out.println("所有任务都已经完成！");
    }
}

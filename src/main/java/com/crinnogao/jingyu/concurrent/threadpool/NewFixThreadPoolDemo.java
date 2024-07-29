package com.crinnogao.jingyu.concurrent.threadpool;

import java.util.concurrent.*;

/**
 * 使用 Executors.newFixedThreadPool(int nThreads) 方法创建一个固定长度的线程池
 * 阻塞队列：LinkedBlockingQueue
 */
public class NewFixThreadPoolDemo {


    public static void main(String[] args) {
        // 创建一个固定长度是5的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // 提交任务到线程池
        /*
         * 超过固定的5个线程之外的任务会被放进一个无界阻塞队列 LinkedBlockingQueue
         * newFixedThreadPool构造函数 源码：
         *     public static ExecutorService newFixedThreadPool(int nThreads) {
         *         return new ThreadPoolExecutor(nThreads, nThreads,
         *                 0L, TimeUnit.MILLISECONDS,
         *                 new LinkedBlockingQueue<Runnable>());
         *     }
         */
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

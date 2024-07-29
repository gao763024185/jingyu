package com.crinnogao.jingyu.concurrent.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用 ThreadPoolExecutor 创建线程池
 * ThreadPoolExecutor：最原始的创建线程池的方式，它包含了7个参数可供设置，这些参数允许开发者细致地控制线程池的行为，
 * 如核心线程数、最大线程数、存活时间、工作队列等。具体参数包括：
 * corePoolSize（核心线程数）
 * maximumPoolSize（最大线程数）
 * keepAliveTime（空闲线程的存活时间）
 * TimeUnit（时间单位，与keepAliveTime参数配合使用）
 * workQueue（工作队列，用于存储等待执行的任务）
 * threadFactory（线程工厂，用于创建新线程）
 * handler（拒绝策略，当任务太多来不及处理时，如何拒绝新任务）
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                10,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5),
                Executors.defaultThreadFactory(),
                /*
                  拒绝策略
                  1、AbortPolicy （默认策略） 不执行此任务，直接抛出一个RejectedExecutionException异常
                  2、CallerRunsPolicy 会直接在 ”调用者“ 线程中运  行这个任务
                  3、DiscardPolicy 任务将被丢弃，不抛出异常，也不执行任务
                  4、DiscardOldestPolicy   会丢弃任务队列中的头结点（通常是存活时间最长且未被处理的任务），然后尝试再次提交当前任务
                  5、自定义策略  通过实现RejectedExecutionHandler接口
                 */
                new ThreadPoolExecutor.AbortPolicy()
        );

        for (int i = 0; i < 100; i++) {
            int taskId = i;
            threadPoolExecutor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " 开始执行任务 " + taskId);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " 完成任务 " + taskId);
            });
        }
        threadPoolExecutor.shutdown();
    }
}

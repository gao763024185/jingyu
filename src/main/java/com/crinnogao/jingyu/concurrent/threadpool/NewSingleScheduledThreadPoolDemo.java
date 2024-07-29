package com.crinnogao.jingyu.concurrent.threadpool;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 使用 Executors.newSingleThreadScheduledExecutor(5); 单线程数的可执行延迟任务的线程池
 * 阻塞队列：DelayedWorkQueue  优先级无界队列 优先延时最短的任务
 */
public class NewSingleScheduledThreadPoolDemo {


    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        // 提交任务到线程池
        /*
         *     public ScheduledThreadPoolExecutor(int corePoolSize) {
         *         super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
         *                 new ScheduledThreadPoolExecutor.DelayedWorkQueue());
         *     }
         *     super--->ThreadPoolExecutor
         *     5个任务都在1s中之后开始执行任务 for执行时间极短
         */
        for (int i = 0; i < 5; i++) {
            int taskId = i;
            // 安排任务在10s后执行
            executorService.schedule(() -> {
                System.out.println(Thread.currentThread().getName() + " 开始执行任务 " + taskId);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " 完成任务 " + taskId);
            }, 1, TimeUnit.SECONDS);

            // 安排周期性执行任务 3秒后首次执行 之后每隔5秒执行一次
            if (taskId == 3) {
                Runnable runnable = () -> {
                    System.out.println(Thread.currentThread().getName() + " 周期性任务执行 " + taskId);
                };
                executorService.scheduleAtFixedRate(runnable, 2, 5, TimeUnit.SECONDS);
            }
        }
        // 关闭线程池（不接受新任务） 等待任务完成  不调用shutdown()方法时，周期性任务会一直执行下去
//        executorService.shutdown();

        // 等待所有任务完成
        while (!executorService.isTerminated()) {
//            System.out.println("有任务未完成！请等待~~~");
        }

        System.out.println("所有任务都已经完成！");
    }
}

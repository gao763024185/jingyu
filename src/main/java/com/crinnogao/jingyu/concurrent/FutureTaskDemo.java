package com.crinnogao.jingyu.concurrent;

import java.util.concurrent.*;

public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Executors 工具类
        // Executor 接口 能执行线程任务
        // ExecutorService 继承 并拓展了 Executor接口 提供了更多的方法可以获得任务执行的状态并且可以获取任务的返回值
        // 创建一个具有固定线程池大小的 ExecutorService
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 创建一个Callable任务
        Callable<Integer> task = () -> {
            //模拟耗时任务
            TimeUnit.SECONDS.sleep(1);
            return 123;
        };

        // 将Callable任务包装成 FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(task);

        //提交 FutureTask 给 ExecutorService 执行
        executorService.submit(futureTask);
        System.out.println("任务开始执行：");
        //可以做一些其他任务
        System.out.println("我在打王璟煜！");
        // 等待任务完成并获取结果
        Integer result = futureTask.get();

        System.out.println("Result from future task :" + result);

        //关闭 ExecutorService
        executorService.shutdown();

    }
}

package com.crinnogao.jingyu.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 一个或多个线程等待其他线程完成一组操作
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        int studentNum = 6;
        CountDownLatch countDownLatch = new CountDownLatch(studentNum);

        // 模拟6个学生完成作业
        for (int i = 0; i < studentNum; i++) {
            new Thread(
                    () -> {
                        System.out.println(Thread.currentThread().getName() + " 完成作业！");
                        // 学生完成作业计数器-1
                        countDownLatch.countDown();
                        ;
                    }
                    , "学生" + i).start();
        }

        // 班长等待所有学生完成作业
        // 阻塞，直到计数器减到0
        countDownLatch.await();
        System.out.println("班长关门离开教室！");
    }
}

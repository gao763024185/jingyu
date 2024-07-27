package com.crinnogao.jingyu.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 多个线程互相等待 直到所有线程都到达某个屏障点
 */
public class CyclicBarrierDemo2 {

    private static final int NUM_ATHLETES = 4;

    public static void main(String[] args)  {
        CyclicBarrier barrier = new CyclicBarrier(NUM_ATHLETES, () -> {
            System.out.println("所有运动员已就位，准备开始下一棒！");
        });

        for (int i = 0; i < NUM_ATHLETES; i++) {
            new Thread(() -> {
                try {
                    System.out.println("运动员 " + Thread.currentThread().getName() + " 到达交界点");
                    // 等待其他运动员到达（大家互相等待）
                    barrier.await();
                    System.out.println("运动员 " + Thread.currentThread().getName() + " 开始传递下一棒");
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }

            }, "运动员" + i).start();

        }
    }
}

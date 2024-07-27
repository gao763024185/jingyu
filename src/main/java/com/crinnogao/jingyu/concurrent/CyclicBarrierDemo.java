package com.crinnogao.jingyu.concurrent;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    static class Worker implements Runnable {
        private final int id;

        private final CyclicBarrier cyclicBarrier;

        public Worker(int id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }


        @Override
        public void run() {
            try {
                // 模拟任务执行
                System.out.println("Worker " + id + "is working.");
                Thread.sleep(1000);

                //到达屏障点
                cyclicBarrier.await();

                //所有线程都到达屏障点后执行的操作
                System.out.println("Worker " + id + "is done with barrier.");

                //如果需要，C预处理Barrier可以重用

                // barrier.reset(); // 重新设置屏障

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int numThreads = 5;
        CyclicBarrier barrier = new CyclicBarrier(numThreads, () -> {
            System.out.println("All workers hava reached the barrier.");
        });

        Thread[] workers = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {

            workers[i] = new Thread(new Worker(i, barrier));
            workers[i].start();

        }

        //等待所有线程执行完成
        for (Thread t : workers) {
            try {
                t.join();
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }

    }
}

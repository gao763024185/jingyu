package com.crinnogao.jingyu.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class AQSLockDemo {
    // 自定义一个同步器
    private static class Sync extends AbstractQueuedSynchronizer {

        //  尝试获取锁
        @Override
        protected boolean tryAcquire(int acquires) {
            // 检查当前线程是否已经拥有锁
            if (getExclusiveOwnerThread() != Thread.currentThread()) {
                // 锁未被当前线程持有，且状态为0（表示锁未被任何线程持有）
                if (getState() == 0 && compareAndSetState(0, 1)) {
                    //设置当前线程为锁的拥有者
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            }
            return false;

        }

        // 尝试释放锁
        @Override
        protected boolean tryRelease(int releases) {
            // 检查当前线程是否是锁的拥有者
            if (getExclusiveOwnerThread() == Thread.currentThread()) {
                // 释放锁，将状态重置为0
                setState(0);
                // 清除锁的拥有者
                setExclusiveOwnerThread(null);
                return true;
            }
            return false;
        }

        // 判断锁是否被当前线程持有
        public boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }
    }

    // 加锁方法
    public void lock() {
        // 调用AQS的release方法获取锁
        sync.acquire(1);
    }

    // 解锁方法
    public void unlock() {
        // 调用AQS的release方法释放锁
        sync.release(1);
    }

    // 判断锁是否被持有
    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    //自定义同步器
    private final Sync sync = new Sync();

    // 示例使用
    public static void main(String[] args) {
        AQSLockDemo lock = new AQSLockDemo();
        // 线程1尝试获取锁
        new Thread(() -> {
            lock.lock();

            try {
                System.out.println("Thread 1 locked the lock.");
                // 模拟业务处理
                Thread.sleep(200000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
                System.out.println("Thread 1 released the lock");
            }
        }).start();

        // 线程2尝试获取锁
        new Thread(() -> {
            lock.lock();

            try {
                System.out.println("Thread 2 locked the lock.");
                // 模拟业务处理
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
                System.out.println("Thread 2 released the lock");
            }
        }).start();

    }
}


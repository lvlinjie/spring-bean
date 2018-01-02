package com.test;

import java.util.concurrent.CountDownLatch;

/**
 * 有一个任务，需要别的任务执行完之后，才能执行
 * CountDownLatch   倒及时器
 */
public class Task {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(3);
        //模拟一人子任务
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                    System.out.println("a子任务完成" + Thread.currentThread().getName() + "在执行");
                    Thread.sleep((long) (Math.random() * 1000));
                    System.out.println("执行完成 ");
                    latch.countDown();//倒计时-1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                    System.out.println("aab子任务完成" + Thread.currentThread().getName() + "在执行");
                    Thread.sleep((long) (Math.random() * 1000));
                    System.out.println("执行完成 ");
                    latch.countDown();//倒计时-1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                    System.out.println("c子任务完成" + Thread.currentThread().getName() + "在执行");
                    Thread.sleep((long) (Math.random() * 1000));
                    System.out.println("执行完成 ");
                    latch.countDown();//倒计时-1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        //主任务开始执行
        System.out.println("main等待三个任执行完毕，开始执行了……");

        //等待子线程执行完毕
        latch.await();
        //继续执行主线程
        System.out.println("主任务执行" + Thread.currentThread().

                getName());
    }

}

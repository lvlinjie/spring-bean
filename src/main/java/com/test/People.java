package com.test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 一手交钱，一手交贷
 * 两个线程之间进行数据交换的
 * 用于两个线程之间交换数据
 * 还可以用于遗传算法
 * 用于校对工作
 * Exchanger
 */
public class People {
    public static void main(String[] args) {
        //线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        /**
         * 中间物品，可以是任意类
         */
        final Exchanger<String> exchanger = new Exchanger<String>();

        //绑架a
        pool.execute(new Runnable() {
            public void run() {
                try {
                    String renzhi = "b";
                    String money = exchanger.exchange(renzhi);
                    System.out.println("绑架者a用B换回" + money);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        pool.execute(new Runnable() {
            public void run() {
                try {
                    String money = "1000";
                    String renzhi = exchanger.exchange(money);
                    System.out.println("家属用1000万交换了人" + renzhi);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        pool.shutdown();
    }

}

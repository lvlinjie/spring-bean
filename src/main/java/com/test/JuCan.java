package com.test;

import sun.java2d.pipe.SpanIterator;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 公司聚餐，当所有人到期开始吃饭，人没有到期，就等待
 * 同步屏障
 * 统计之后，算总帐，合并
 */
public class JuCan {
    public static void main(String[] args) {
        //设置一个同步屏障
        final CyclicBarrier cb = new CyclicBarrier(3);
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {

            final int user = i + 1;
            Runnable runnable = new Runnable() {

                public void run() {
                    try {
                        //每个人来的时间不一样
                        Thread.sleep((long) (Math.random()*1000));
                        System.out.println(user+"当前到达了"+cb.getNumberWaiting()+"人到达");

                        //等待，只有线程之后才会往下走
                        cb.await();
                        if(user==1){
                            System.out.println("人员到期，开始吃饭");
                        }
                        Thread.sleep((long) (Math.random()*1000));
                        System.out.println(user+"吃了饭了，准备回家 ……");
                        //CyclicBarrier可以同时使用，可以干别的事情，循环使用屏障

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            pool.execute(runnable);
        }
        pool.shutdown();
    }
}

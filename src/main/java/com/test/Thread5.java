package com.test;

public class Thread5 {
    public static void main(String[] args) {
        Thread t1=new Thread(new Runnable() {
            public void run() {
                try {
                    for (int i =0;i<30;i++){
                        Thread.sleep(1000);
                        System.out.println("子线程"+i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        t1.setDaemon(true);//把非守护线程转换成守护线程
        t1.start();
        for (int i =0;i<5;i++){
            System.out.println("主线程：：：：：");
        }
        System.out.println("主线程执行完毕----");
    }
}

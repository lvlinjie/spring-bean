package com.test;

public class JoinThread {

    public static void main(String[] args) throws InterruptedException {


        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 30; i++) {
                    System.out.println("子线程" + i);
                }
            }
        });
        t1.start();
        t1.join();
        for (int i = 0; i < 4; i++) {

            System.out.println("主线程" + i);
        }
    }

}

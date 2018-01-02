package com.test;

public class Test001 {

    public static void main(String[] args) throws InterruptedException {

        final Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 2; i++) {
                    System.out.println("t1-->" + i);
                }
            }
        });
        final Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 2; i++) {
                    System.out.println("t2-->" + i);
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {

            public void run() {
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 2; i++) {
                    System.out.println("t3->" + i);
                }
            }
        });
        t3.start();
        t2.start();
        t1.start();
    }
}

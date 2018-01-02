package com.test;

class ThreadTrain implements Runnable {


    private int count = 100;

    public void run() {
        while (count > 0) {
            try {
                Thread.sleep((long) (Math.random() * 1000));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sale();
        }
    }


    public void sale() {
        if (count > 0) {
            System.out.println(Thread.currentThread().getName() + ",出票第" + (100 - count + 1) + "中");
            count--;
        }
    }
}

class demo {
    public static void main(String[] args) {
        ThreadTrain threadTrain = new ThreadTrain();
        Thread t1 = new Thread(threadTrain, "窗口1");
        Thread t2 = new Thread(threadTrain, "窗口1");
        t1.start();
        t2.start();
    }
}
package com.test;

class Train implements Runnable {


    //总共有100张票
    private int count = 100;


    public void run() {
        //为了让模拟程一起抢
        while (count > 0) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sale();
        }
    }


    public  synchronized void sale() {
        //出票中
        synchronized (this) {//这里加是同步代码块，给o加了一把锁。写完之后，会对o释放锁，下一个线程也才会拿到锁
            if (count > 0) {
                System.out.println(Thread.currentThread().getName() + ",出票第" + (100 - count + 1) + "中");
                count--;
            }

        }
    }
}


public class testDemo {
    public static void main(String[] args) {
        Train train = new Train();
        Thread t1 = new Thread(train, "窗口1");
        Thread t2 = new Thread(train, "窗口2");
        t1.start();
        t2.start();
    }
}
package com.test;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * 原子变量实现线程同步
 * 原子操作就是指将读取变量值、修改变量值、保存变量值看成一个整体来操作
 * 即-这几种行为要么同时完成，要么都不完成。
 * 在java的util.concurrent.atomic包中提供了创建了原子类型变量的工具类，
 * 使用该类可以简化线程同步。
 * 其中AtomicInteger 表可以用原子方式更新int的值，可用在应用程序中(如以原子方式增加的计数器)
 * AtomicInteger类常用方法：
 * AtomicInteger(int initialValue) : 创建具有给定初始值的新的AtomicInteger
 * addAddGet(int dalta) : 以原子方式将给定值与当前值相加
 * get() : 获取当前值
 */

public class SynchronizedThread2 {

    class Bank {

        private AtomicInteger account = new AtomicInteger(100);

        public AtomicInteger getAccount() {
            return account;
        }

        public void save(int money) {
            account.addAndGet(money);
        }

    }

    class NewThread implements Runnable {
        private Bank bank;

        public NewThread(Bank bank) {
            this.bank = bank;
        }


        public void run() {
            for (int i = 0; i < 10; i++) {
                // bank.save1(10);
                bank.save(10);
                System.out.println(i + "账户余额为：" + bank.getAccount());
            }
        }

    }

    /**
     * 建立线程，调用内部类
     */
    public void useThread() {
        Bank bank = new Bank();
        NewThread new_thread = new NewThread(bank);
        System.out.println("线程1");
        Thread thread1 = new Thread(new_thread);
        thread1.start();
        System.out.println("线程2");
        Thread thread2 = new Thread(new_thread);
        thread2.start();
    }

    public static void main(String[] args) {
        SynchronizedThread2 st = new SynchronizedThread2();
        st.useThread();
    }

}
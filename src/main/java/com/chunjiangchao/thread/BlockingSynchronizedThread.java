package com.chunjiangchao.thread;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 用阻塞队列实现线程同步 LinkedBlockingQueue的使用
 * 队列是先进先出的顺序（FIFO
 * LinkedBlockingQueue 类常用方法
 * LinkedBlockingQueue() : 创建一个容量为Integer.MAX_VALUE的LinkedBlockingQueue
 * put(E e) : 在队尾添加一个元素，如果队列满则阻塞
 * size() : 返回队列中的元素个数
 * take() : 移除并返回队头元素，如果队列空则阻塞
 */
public class BlockingSynchronizedThread {

    /**
     * 定义一个阻塞队列用来存储生产出来的商品
     */
    private LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();


    /**
     * 定义生产商品个数
     */
    private static final int size = 10;

    /**
     * 定义启动线程的标志，为0时，启动生产商品的线程；为1时，启动消费商品的线程
     */
    private int flag = 0;

    private class LinkBlockThread implements Runnable {
        public void run() {
            int new_flag = flag++;
            System.out.println("启动线程 " + new_flag);

            if (new_flag == 0) {
                for (int i = 0; i < size; i++) {
                    int b = new Random().nextInt(255);//随即生成了一个带编号的商品
                    System.out.println("生产商品：" + b + "号");
                    try {
                        queue.put(b);//把生产后的商品放到队列中
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //queue.size()就是获取队列中的个数
                    System.out.println("仓库中还有商品：" + queue.size() + "个");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } else {
                for (int i = 0; i < size / 2; i++) {
                    try {
                        //移出队列中最前面的商品
                        int n = queue.take();//
                        System.out.println("消费者买去了" + n + "号商品");
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("仓库中还有商品：" + queue.size() + "个");
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        BlockingSynchronizedThread bst = new BlockingSynchronizedThread();
        LinkBlockThread lbt = bst.new LinkBlockThread();
        Thread thread1 = new Thread(lbt);
        Thread thread2 = new Thread(lbt);
        thread1.start();
        thread2.start();

    }
}

/**
 * 注：BlockingQueue定义了阻塞队列的常用方法，尤其是三种添加元素的方法，我们要多加注意，当队列满时：
 * <p>
 * 　　add()方法会抛出异常
 * <p>
 * 　　offer()方法返回false
 * <p>
 * 　　put()方法会阻塞
 */

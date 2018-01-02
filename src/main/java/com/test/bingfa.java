package com.test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 20个人去买票，窗口只有两个，当一个人离开窗口，在等待的人中自动 会有人去买
 * 控制并发量
 * semaphore信号量
 * 人=线程
 * 2个窗口=资源
 * 买票=执行线程
 * 离开窗口=执行完毕
 * 等待=线程阻塞
 * 解决方案：semaphore信号量
 */
public class bingfa {

    class MyTask implements Runnable {
        /**
         * 构造一个可以或得信号量的线程
         */
        private Semaphore semaphore;//信号量
        private int user;//第几个用户


        public MyTask(Semaphore s, int user) {
            this.semaphore = s;
            this.user = user;
        }

        public void run() {
            try {
                //获取信号，占有窗口，才能买票
                semaphore.acquire();
                System.out.println("用户" + user + "进入窗口，准备买票……");
                Thread.sleep((long) (Math.random() * 1000));
                System.out.println("用户" + user + "买完票，准备离开……");
                Thread.sleep((long) (Math.random() * 1000));
                semaphore.release();//释放信号 ，让别人获取到信号
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void execu() {
        //定义窗口.现在有两个
        final Semaphore s = new Semaphore(2);
        //线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            threadPool.execute(new MyTask(s,(i+1)));
        }
    }

    public static void main(String[] args) {
        bingfa demo  = new bingfa();
        demo.execu();
    }
}
/*
*用于量控制，限流
*  spring cloud hystrix （Semaphore信号量，线程池+队列）   限流，熔断，降级
*如果人太多，可以保护服务器不会崩溃
*/
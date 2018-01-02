package cn.tets.thread.learn;

import com.test.Thread1;

public class Mian {
    public static void main(String[] args) {
        Thread1 t1  = new Thread1("a");
        Thread1 t2  = new Thread1("b");
        t1.start();
        t2.start();
    }
}

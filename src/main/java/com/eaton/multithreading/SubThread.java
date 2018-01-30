package com.eaton.multithreading;

/**
 * Created by yitgeng on 1/29/2018.
 */
public class SubThread extends Thread {

    public SubThread() {
        //调用父类的String参数的构造方法，指定线程的名称
        super();
    }

    public SubThread(String name) {
        //调用父类的String参数的构造方法，指定线程的名称
        super(name);
    }

    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println("Sub thread " + Thread.currentThread().getName() + ": " + i);
        }
    }

}

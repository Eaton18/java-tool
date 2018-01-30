package com.eaton.multithreading;

/**
 * Created by yitgeng on 1/29/2018.
 */
public class MainThread {
    public static void main(String[] args) {
        SubThread subThread = new SubThread();
        subThread.start();
        for (int i = 0; i < 50; i++) {
            System.out.println("Main thread: " + i);
        }
    }
}

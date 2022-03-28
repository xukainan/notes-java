package com.uaian.concurrent.lock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class DeadLock {

    private static String lock1 = "lock1";
    private static String lock2 = "lock2";

    public static void main(String[] args) throws InterruptedException {


        Thread t1 = new Thread(() -> {
            try {
                synchronized (lock1) {
                    System.out.println("t1 获取到了lock1");
                    Thread.sleep(2000);
                    synchronized (lock2) {
                        Thread.sleep(2000);
                        System.out.println("t1 获取到了lock2");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });


        Thread t2 = new Thread(() -> {
            try {
                synchronized (lock2) {
                    System.out.println("t2 获取到了lock2");
                    Thread.sleep(2000);
                    synchronized (lock1) {
                        Thread.sleep(2000);
                        System.out.println("t2 获取到了lock1");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        t1.start();
        t2.start();

        Thread.sleep(2000);

        ThreadMXBean mbean = ManagementFactory.getThreadMXBean();
        // 只能检测 synachrozied 同步代码块 的死锁
        // long[] deadlockedThreadIds = mbean.findMonitorDeadlockedThreads();

        // 可以检测 juc下的 Lock造成的死锁和 synachrozied代码块的死锁
        long[] deadlockedThreadIds = mbean.findDeadlockedThreads();

        if (deadlockedThreadIds != null) {
            ThreadInfo[] threadInfos = mbean.getThreadInfo(deadlockedThreadIds);

            for (ThreadInfo ti : threadInfos) {
                System.out.println(ti);
            }
        }

    }
}

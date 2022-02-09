package com.uaian.algorithm.produce_consumer;

public class WaitNotifyDemo {
    private static Integer COUNT = 0;
    private static final Integer FULL = 10;
    private static String LOCK = "lock";

    public static void main(String[] args) {
        WaitNotifyDemo waitNotifyDemo = new WaitNotifyDemo();
        new Thread(waitNotifyDemo.new Producer()).start();
        new Thread(waitNotifyDemo.new Producer()).start();
        new Thread(waitNotifyDemo.new Producer()).start();
        new Thread(waitNotifyDemo.new Producer()).start();
        new Thread(waitNotifyDemo.new Consumer()).start();
        new Thread(waitNotifyDemo.new Consumer()).start();
    }

    class Producer implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK) {
                    while (COUNT == FULL){
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    COUNT++;
                    System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + COUNT);
                    LOCK.notifyAll();

                }
            }
        }
    }
    
    class Consumer implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK) {
                    while (COUNT == 0){
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    COUNT--;
                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + COUNT);
                    LOCK.notifyAll();
                }
            }
        }
    }
}



package com.uaian.algorithm.produce_consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {

    public static void main(String[] args) {

        BlockingQueue<Object> queue = new ArrayBlockingQueue<>(10);

        Runnable producer = () -> {
            while (true) {
                try {
                    Thread.sleep(3000);
                    queue.put(new Object());
                    System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + queue.size());  //疑问：Thread-0生产者生产，目前总共有0？ 因为打印语句在notify后面，所以可能已经取走了产品，才打印的
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        Runnable consumer = () -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                    queue.take();
                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + queue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}

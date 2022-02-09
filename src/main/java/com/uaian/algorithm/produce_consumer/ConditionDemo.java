package com.uaian.algorithm.produce_consumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {

    private Queue queue = new LinkedList();
    private int max = 10;
    private ReentrantLock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public void put(Object o) throws InterruptedException{
        lock.lock();
        try {
            while (queue.size() == max) {
                notFull.await();
            }
            queue.add(o);
            notEmpty.signalAll();
        }finally {
            lock.unlock();
        }

    }

    public Object take() throws InterruptedException{
        lock.lock();
        try {
            while (queue.size() == 0) {
                notEmpty.await();
            }
            Object remove = queue.remove();
            notFull.signalAll();
            return remove;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionDemo demo = new ConditionDemo();

        Runnable producer = () -> {
            while (true) {
                try {
                    demo.put(new Object());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        Runnable consumer = () -> {
            while (true) {
                try {
                    demo.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }


}

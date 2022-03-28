package com.uaian.jvm;

/**
 * 栈溢出
 * 每个线程都有一个虚拟机栈
 * 线程的开销也是要占用内存的
 * 如果系统中的线程数量过多，那么占用内存的大小也是非常可观的
 *
 * -Xss128K（设置栈大小为 128K）
 */
public class StackOverflowDemo {

    static int count = 0;

    static void a() {
        System.out.println(count);
        count++;
        b();
    }

    static void b() {
        System.out.println(count);
        count++;
        a();
    }

    public static void main(String[] args) throws Exception {
        a();
    }

}

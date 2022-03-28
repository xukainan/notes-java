package com.uaian.jvm;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 直接内存溢出
 * -XX:MaxDirectMemorySize=10M -Xmx10M
 */
public class OffHeapOOMTest {
    public static final int _1MB = 1024 * 1024;
    static List<ByteBuffer> byteList = new ArrayList<>();

    private static void oom(HttpExchange exchange){
        try {
            String res = "Off Heap oom begin!";
            exchange.sendResponseHeaders(200, res.getBytes().length);
            OutputStream os =exchange.getResponseBody();
            os.write(res.getBytes());
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        for (int i = 0;; i++) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(_1MB);
            byteList.add(buffer);
            System.out.println(i + "MB");
            memPrint();
        }
    }

    private static void srv() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8888), 0);
        HttpContext context = server.createContext("/");
        context.setHandler(OffHeapOOMTest::oom);
        server.start();
    }

    public static void main(String[] args) throws IOException {
        srv();
    }

    static void memPrint() {
        for (MemoryPoolMXBean memoryPoolMXBean: ManagementFactory.getMemoryPoolMXBeans()) {
            System.out.println(memoryPoolMXBean.getName() + " committed:" + memoryPoolMXBean.getUsage().getCommitted() +
                    " used:" + memoryPoolMXBean.getUsage().getUsed());
        }
    }
}

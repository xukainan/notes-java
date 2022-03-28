package com.uaian.jvm;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * 堆溢出
 * -Xmx20m -Xmn4m -XX:+UseG1GC -XX:MetaspaceSize=16M -XX:MaxMetaspaceSize=16M -XX:+PrintFlagsFinal -verbose:gc -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintReferenceGC -XX:+PrintHeapAtGC -XX:+PrintTenuringDistribution -Xloggc:C:\jvmlogs\gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\jvmlogs\headlog\ -XX:ErrorFile=C:\jvmlogs\errorlog\hs_error_pid%p.log -XX:-OmitStackTraceInFastThrow
 */
public class OOMDemo {

    public static final int _1MB = 1024 * 1024;
    static List<byte[]> byteList = new ArrayList<>();

    private static void oom(HttpExchange exchange) {
        try {
            String response = "oom begin!";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        for (int i = 0;; i++) {
            byte[] bytes = new byte[_1MB];
            byteList.add(bytes);
            System.out.println(i + "MB");
            memPrint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void memPrint() {
        for (MemoryPoolMXBean memoryPoolMXBean: ManagementFactory.getMemoryPoolMXBeans()) {
            System.out.println(memoryPoolMXBean.getName() + " committed:" + memoryPoolMXBean.getUsage().getCommitted() +
                    " used:" + memoryPoolMXBean.getUsage().getUsed());
        }
    }

    private static void srv() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8888), 0);
        HttpContext context = server.createContext("/");
        context.setHandler(OOMDemo::oom);
        server.start();
    }

    public static void main(String[] args) throws IOException {
        srv();
    }
}

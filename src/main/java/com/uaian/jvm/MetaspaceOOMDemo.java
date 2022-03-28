package com.uaian.jvm;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * 元空间溢出
 * -Xmx20m -Xmn4m -XX:+UseG1GC -XX:MetaspaceSize=16M -XX:MaxMetaspaceSize=16M -XX:+PrintFlagsFinal -verbose:gc -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintReferenceGC -XX:+PrintHeapAtGC -XX:+PrintTenuringDistribution -Xloggc:C:\jvmlogs\gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\jvmlogs\headlog\ -XX:ErrorFile=C:\jvmlogs\errorlog\hs_error_pid%p.log -XX:-OmitStackTraceInFastThrow
 */
public class MetaspaceOOMDemo {
    public interface Facade{
        void m(String input);
    }
    public static class FacadeImpl implements Facade{

        @Override
        public void m(String name) {
        }
    }
    public static class MetaspaceFacadeInvocationHandler implements InvocationHandler{

        private Object impl;

        public MetaspaceFacadeInvocationHandler(Object impl) {
            this.impl = impl;
        }

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            return method.invoke(impl, objects);
        }
    }

    private static Map<String, Facade> classLeakingMap = new HashMap<>();

    private static void oom(HttpExchange exchange){
        try {
            String res = "Meatspace oom begin!";
            exchange.sendResponseHeaders(200, res.getBytes().length);
            OutputStream os =exchange.getResponseBody();
            os.write(res.getBytes());
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            for (int i = 0;; i++) {
                String jar = "file:" + i + ".jar";
                URL[] urls = new URL[]{new URL(jar)};
                URLClassLoader urlClassLoader = new URLClassLoader(urls);

                Facade t = (Facade) Proxy.newProxyInstance(urlClassLoader, new Class<?>[]{Facade.class},
                        new MetaspaceFacadeInvocationHandler(new FacadeImpl()));

                classLeakingMap.put(jar, t);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private static void srv() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8888), 0);
        HttpContext context = server.createContext("/");
        context.setHandler(MetaspaceOOMDemo::oom);
        server.start();
    }

    public static void main(String[] args) throws IOException {
        srv();
    }
}

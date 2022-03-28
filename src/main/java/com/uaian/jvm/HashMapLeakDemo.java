package com.uaian.jvm;

import scala.Int;

import java.util.HashMap;
import java.util.Map;

public class HashMapLeakDemo {
    public static class Key {
        String title;

        public Key(String title) {
            this.title = title;
        }
    }

    public static void main(String[] args) {
        Map<Key, Integer> map = new HashMap<>();
        map.put(new Key("1"), 1);
        map.put(new Key("2"), 2);
        map.put(new Key("3"), 3);

        Integer integer = map.get(new Key("2")); //与上面的new Key("2")并不是同一个对象
        System.out.println(integer);
    }
}

package com.uaian.algorithm.leecode;

import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache_LinkedHashMap extends LinkedHashMap<Integer, Integer> {

    private int capacity;

    public LRUCache_LinkedHashMap(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> entry) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCache_LinkedHashMap lruCache = new LRUCache_LinkedHashMap(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.get(1);
        lruCache.put(3, 3);
        lruCache.get(2);
        lruCache.put(4, 4);
        lruCache.get(1);
        lruCache.get(3);
        lruCache.get(4);
    }
}
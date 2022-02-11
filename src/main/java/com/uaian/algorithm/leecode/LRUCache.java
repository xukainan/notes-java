package com.uaian.algorithm.leecode;

public class LRUCache {

    LinkedHashMap<Integer, Integer> linkedHashMap;

    public LRUCache(int capacity) {
        linkedHashMap = new LinkedHashMap(capacity);
    }

    public int get(int key) {
        Integer res = linkedHashMap.get(key);
        if (res == null) {
            return -1;
        }
        return res;
    }

    public void put(int key, int value) {
        linkedHashMap.put(key, value);
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
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

class LinkedHashMap<K, V> {

    private int size = 0;
    private int capacity;
    private Entry[] entries;
    private Entry<K, V> head;
    private Entry<K, V> tail;

    public LinkedHashMap(int capacity) {
        this.capacity = capacity;
        entries = new Entry[capacity];
    }

    public V get(K key) {
        int hashcode = hashCode(key);
        int index = hashcode % capacity;
        Entry curEntry = entries[index];
        if(curEntry != null) {
            Entry entry = getVal(curEntry, key);
            if(entry != null) {
                afterEntryGet(entry);
                return (V)entry.val;
            }
            return null;

        }
        return null;
    }

    private void afterEntryGet(Entry entry) {
        Entry<K, V> oldTail = this.tail;
        this.tail = entry;
        Entry<K, V> oldBefore = oldTail.before;
        if(head.hashcode == entry.hashcode && head.key == entry.key){
            head = entry.after;
        }
        if(oldBefore.hashcode == entry.hashcode && oldBefore.key == entry.key){
            oldTail.before = entry.before;
            oldBefore.after = oldTail;
        }
        oldTail.after = this.tail;
        this.tail.after = null;
        this.tail.before = oldTail;
    }

    private Entry getVal(Entry curNode, K k) {
        if(curNode != null) {
            if(curNode.key == k || k.equals(curNode.key)){
                return curNode;
            }else{
                getVal(curNode.next, k);
            }
        }
        return null;
    }

    public void put(K key, V value) {
        if(this.size >= this.capacity) {
            removeEldestEntry();
        }
        int hashcode = hashCode(key);
        int index = hashcode % capacity;
        Entry curEntry = entries[index];
        if(curEntry == null) {
            entries[index] = newEntry(key, value, hashcode, null);
            size++;
        }else {
            putVal(curEntry, key, value, hashcode);
        }

    }

    private void removeEldestEntry() {
        //从双向链表删除
        Entry<K, V> oldHead = this.head;
        Entry<K, V> newHead = this.head.after;
        newHead.before = null;
        this.head = newHead;
        //从HashMap删除
        remove(oldHead.key);
    }

    private void remove(K key) {
        int hash = hashCode(key);
        int index = hash % capacity;
        Entry entry = entries[index];
        if(key.equals(entry.key)) {
            if(entry.next != null) {
                entries[index] = entry.next;
                size--;
            }else {
                entries[index] = null;
                size--;
            }
        }else {
            removeVal(entry, entry.next, key);
        }
    }

    private void removeVal(Entry lastEntry, Entry curEntry, K key) {
        if(curEntry != null) {
            if (key.equals(curEntry.key)) {
                lastEntry.next = curEntry.next;
                size--;
                return;
            }
            removeVal(curEntry, curEntry.next, key);
        }
    }

    private Entry newEntry(K key, V value, int hashcode, Entry next) {
        Entry curTail = tail;
        tail = new Entry(key, value, hashcode, null);
        if (curTail == null) {
            this.head = tail;
        } else {
            tail.before = curTail;
            curTail.after = tail;
        }
        return tail;
    }

    private void putVal(Entry curEntry, K key, V value, int hashcode) {
        K curKey = (K) curEntry.key;
        if(curKey.equals(key)) {
            curEntry.val = value;
        }else {
            if(curEntry.next == null) {
                size++;
                curEntry.next = newEntry(key, value, hashcode, null);
                return;
            }
            putVal(curEntry.next, key, value, hashcode);
        }
    }

    public int hashCode(K k) {
        return k.hashCode();
    }



}

class Entry<K, V> {
    K key;
    V val;
    int hashcode;
    Entry<K, V> before;
    Entry<K, V> after;
    Entry<K, V> next;

    public Entry(K key, V val, int hashcode, Entry<K, V> next) {
        this.key = key;
        this.val = val;
        this.hashcode = hashcode;
        this.next = next;
    }
}

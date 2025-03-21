package com.neo.base;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class GlobalCache<K, V> {
    // 使用 ConcurrentHashMap 实现线程安全的缓存
    private final ConcurrentHashMap<K, V> cache;

    public GlobalCache() {
        // 初始化缓存
        this.cache = new ConcurrentHashMap<>();
    }

    // 存储数据到缓存
    public void put(K key, V value) {
        cache.put(key, value);
    }

    // 从缓存中获取数据
    public V get(K key) {
        return cache.get(key);
    }

    // 判断缓存中是否存在某个key
    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    // 移除缓存中的某个键值对
    public void remove(K key) {
        cache.remove(key);
    }

    // 清除所有缓存
    public void clear() {
        cache.clear();
    }

    // 获取缓存的大小
    public int size() {
        return cache.size();
    }

    // 简单的示例使用（例如带过期时间）
    public void putWithExpiration(K key, V value, long duration, TimeUnit timeUnit) {
        cache.put(key, value);
        // 此处可以实现过期逻辑，通过定时任务或其他机制
        // 在实际应用中，可以利用ScheduledExecutorService来定期清理过期缓存
    }



    public static void main(String[] args) {



        // 示例使用
        GlobalCache<String, String> cache = new GlobalCache<>();
        cache.put("key1", "value1");
        System.out.println("key1: " + cache.get("key1"));

        // 更新缓存值
        cache.put("key1", "newValue1");
        System.out.println("Updated key1: " + cache.get("key1"));

        // 检查缓存大小
        System.out.println("Cache Size: " + cache.size());

        // 移除缓存中的key1
        cache.remove("key1");
        System.out.println("After removing key1: " + cache.get("key1"));
    }
}


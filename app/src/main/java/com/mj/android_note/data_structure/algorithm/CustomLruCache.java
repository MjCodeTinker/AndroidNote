package com.mj.android_note.data_structure.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author      : MJ
 * Date        : 2020-02-10--13:05
 * Email       : miaojian_666@126.com
 * Description : 简单实现lruCache的功能
 */
public class CustomLruCache<K, V> {

    // 按照访问顺序结构的linkedHashMap
    private LinkedHashMap<K, V> linkedHashMap;

    // 存储大小
    private int size;

    // 最大存储容量
    private int maxSize;

    // 构造方法设置最大存储容量
    public CustomLruCache(int maxSize) {
        this.maxSize = maxSize;
        linkedHashMap = new LinkedHashMap<>(0, 0.75f, true);
    }

    // 计算当前要存储对象的大小
    public int sizeOf(K k, V v) {
        return 1;
    }

    // 重新调整容器中的缓存内容
    private void trimToSize(int maxSize) {
        while (true) {
            K k;
            V v;
            // 没有大于最大存储大小
            if (size <= maxSize) {
                break;
            }
            // 超出了容器的容量
            // 取出链表的头结点，并remove
            Map.Entry<K, V> head = linkedHashMap.entrySet().iterator().next();
            k = head.getKey();
            v = head.getValue();
            linkedHashMap.remove(k);
            size -= sizeOf(k, v);
        }
    }

    public V get(K k) {
        return linkedHashMap.get(k);
    }

    public void put(K k, V v) {
        V put = linkedHashMap.put(k, v);
        size += sizeOf(k, v);
        if (put != null) {
            size -= sizeOf(k, v);
        }
        trimToSize(maxSize);
    }


}

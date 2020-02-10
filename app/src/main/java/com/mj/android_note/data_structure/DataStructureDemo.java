package com.mj.android_note.data_structure;

import android.util.LruCache;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class DataStructureDemo {

    public static void main(String[] args) {
        // HashMap
//        HashMap<Object, Object> map = new HashMap<>();
//        map.put(1, "2");
//        map.put("2", 1);
//        Integer integer = (Integer) map.get("2");
//        System.out.println("integer:" + integer);

        //Stack
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < 10; i++) {
            stack.push("" + i);
        }
        printLog(stack.toString());
        String peek = stack.peek();
        printLog("peekElement = " + peek + "---" + stack.toString());
        String pop = stack.pop();
        printLog("popElement = " + pop + "---" + stack.toString());
        stack.add("MJ");
        String pop1 = stack.pop();
        printLog("pop1Element = " + pop1 + "---" + stack.toString());

        // 自定义stack
        CustomStack<Integer> customStack = new CustomStack<>();
        customStack.push(1);
        customStack.push(2);
        printLog("peek = " + customStack.peek() + "--customStack: " + customStack.toString());
        printLog("pop = " + customStack.pop() + "--customStack : " + customStack.toString());

        // LinkedHashMap
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>(10, 0.75f, true);
        linkedHashMap.put("4", 1);
        linkedHashMap.put("2", 2);
        linkedHashMap.put("1", 3);
        linkedHashMap.put("3", 4);
        printLog("LinkedHasMap #####原始存放数据 ：");
        printMap(linkedHashMap);
        linkedHashMap.put("3", 6);
        linkedHashMap.get("2");
        printLog("LinkedHasMap #####put get 之后 ：");
        printMap(linkedHashMap);

        // HashMap,是无序的
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("3", 1);
        hashMap.put("4", 2);
        hashMap.put("2", 3);
        hashMap.put("1", 4);
        // put 之后的返回值是map中的原始值
        Integer put = hashMap.put("6", 5);
        printLog("put = " + put);
        printLog("HashMap ####原始存储的数据");
        printMap(hashMap);

        // 总结：
        // 1.为什么HashMap是无序的
        // 2.为什么LinkedHashMap是有序的
        // 3.LinkedHashMap是如何保证按照访问顺序，将数据存在链表的尾部的。
        // 4.HashMap中的entrySet是何拿值的

    }

    private static void printMap(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> next : map.entrySet()) {
            String key = next.getKey();
            Integer value = next.getValue();
            printLog("key : " + key + "--value :" + value);
        }
    }

    // 自定义栈的实现
    private static class CustomStack<T> {

        private List<T> data;

        CustomStack() {
            data = new ArrayList<>();
        }

        synchronized T pop() {
            T peek = peek();
            data.remove(peek);
            return peek;
        }

        synchronized T peek() {
            int size = data.size();
            if (size == 0) {
                return null;
            }
            return data.get(size - 1);
        }

        synchronized void push(T t) {
            data.add(t);
        }

        synchronized boolean isEmpty() {
            return data.size() == 0;
        }

        @Override
        public String toString() {
            return "CustomStack{" +
                    "data=" + data +
                    '}';
        }
    }


    private static void printLog(String str) {
        System.out.println(str);
    }

}

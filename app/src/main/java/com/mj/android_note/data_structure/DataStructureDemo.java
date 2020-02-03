package com.mj.android_note.data_structure;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

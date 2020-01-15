package com.mj.android_note.data_structure;

import java.util.HashMap;

public class DataStructureDemo {

    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put(1, "2");
        map.put("2", 1);
        Integer integer = (Integer) map.get("2");
        System.out.println("integer:" + integer);
    }

}

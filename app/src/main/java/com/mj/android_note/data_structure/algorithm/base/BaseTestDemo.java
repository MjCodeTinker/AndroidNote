package com.mj.android_note.data_structure.algorithm.base;

import java.util.Random;

public class BaseTestDemo {


    public static void main(String[] args) {


        BaseAlgorithm baseAlgorithm = new BaseAlgorithm();

        // 冒泡排序
        int[] count = new int[10000];
        Random random = new Random();
        baseAlgorithm.printData(count);
        System.out.println("bubbling sort ###########");

        for (int i = 0; i < count.length; i++) {
            count[i] = random.nextInt(10000);
        }

        baseAlgorithm.printData(baseAlgorithm.bubbling(count));

    }


}

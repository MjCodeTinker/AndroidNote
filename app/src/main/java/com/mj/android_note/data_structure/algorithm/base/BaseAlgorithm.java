package com.mj.android_note.data_structure.algorithm.base;

import java.util.Arrays;

public class BaseAlgorithm {

    static final int[] SORT_DATA = {
            1, 20, 3, 7, 6, 15
    };

    /**
     * 冒泡排序
     * 原理：
     *
     * @param data 被排序的数组
     * @return 排序结果
     */
    int[] bubbling(int[] data) {
        boolean swap;
        // 记录执行的次数
        int loopCount = 0;
        for (int i = 0; i < data.length; i++) {
            swap = false;
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    swap = true;
                }
                loopCount++;
            }
            if (!swap) {
                break;
            }
        }

        System.out.println("loopCount = " + loopCount);
        return data;
    }


    /**
     * 打印数据
     *
     * @param data 数据
     */
    void printData(int[] data) {
        System.out.println("data : " + Arrays.toString(data));
    }


}

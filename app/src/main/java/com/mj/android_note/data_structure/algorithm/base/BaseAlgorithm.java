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


    /**
     * 如果数组是单调递增或单调递减的，那么它是单调的。
     * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
     * 当给定的数组 A 是单调数组时返回 true，否则返回 false
     * <p>
     * 示例 1：
     * <p>
     * 输入：[1,2,2,3]
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：[6,5,4,4]
     * 输出：true
     * 示例 3：
     * <p>
     * 输入：[1,3,2]
     * 输出：false
     * 示例 4：
     * <p>
     * 输入：[1,2,4,5]
     * 输出：true
     * 示例 5：
     * <p>
     * 输入：[1,1,1]
     * 输出：true
     * <p>
     * 提示：
     * <p>
     * 1 <= A.length <= 50000
     * -100000 <= A[i] <= 100000
     * <p>
     * 思路：
     * 本题就是不太好判断是增还是减还是都相等，所以，可以比较第一个元素和最后一个元素，然后就可以分为三种情况了
     * 如果满足单调函数，要么是递增，要么是递减,所以可以把数组的第一个数和最后一个数进行比较
     */

    // 检查一个数列是否是单调的。
    public boolean checkArrayIsMonotonous(int[] A) {
        int length = A.length;
        if (A[0] == A[length - 1]) {
            // 除非是全部相等的数列，才能是单调的
            for (int i = 0; i < length - 1; i++) {
                if (A[0] != A[i]) {
                    return false;
                }
            }
        } else if (A[0] > A[length - 1]) {
            // 可能为递减序列
            for (int i = 0; i < length - 1; i++) {
                if (A[0] < A[i]) {
                    return false;
                }
            }
        } else {
            // 可能为递增序列
            for (int i = 0; i < length - 1; i++) {
                if (A[0] > A[i]) {
                    return false;
                }
            }
        }

        return true;
    }


    // 快速排序使用到的数据源
    public static final int[] DATA_QUICK_SORT = {3, 8, 6, 5, 9, 2, 1, 0, 4, 7};

    /**
     * 快速排序算法
     *
     * @param array
     * @param left
     * @param right
     * @return
     */
    private int partition(int[] array, int left, int right) {

        // 基准元素
        int temp = array[left];

        while (left < right) {
            // 从右向左找出比基准元素小的元素
            while (left < right && temp <= array[right]) {
                right--;
            }

            if (left < right) {
                array[left] = array[right];
                left++;
            }

            // 从左向右找，比基准元素大的元素
            while (left < right && temp >= array[left]) {
                left++;
            }

            if (left < right) {
                array[right] = array[left];
                right--;
            }

        }

        // 找到基准元素最终的位置了，此时基准元素左边的都是比他小的，右边的都是比他大的
        array[left] = temp;
        return left;
    }

    public void quickSort(int[] array, int left, int right) {
        if (array == null || array.length == 0 || left > right) {
            return;
        }
        int middle = partition(array, left, right);
        quickSort(array, left, middle - 1);
        quickSort(array, middle + 1, right);
    }


}

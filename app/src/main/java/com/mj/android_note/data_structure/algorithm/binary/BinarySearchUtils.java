package com.mj.android_note.data_structure.algorithm.binary;

/**
 * Author      : MJ
 * Date        : 2020-03-17--13:53
 * Email       : miaojian_666@126.com
 * Description : 二分查找法
 */
public class BinarySearchUtils {

    /**
     * 二分查找
     *
     * @param array  给定一个数组
     * @param target 目标值要找的目标值
     * @return 目标值在数组中的索引
     */
    public static int binarySearch(int[] array, int target) {
        int startIndex = 0;
        int endIndex = array.length - 1;
        int searchCount = 0;
        while (startIndex <= endIndex) {
            int midIndex = (startIndex + endIndex) / 2;
            int val = array[midIndex];
            searchCount++;
            if (target > val) {
                startIndex = midIndex + 1;
            } else if (target < val) {
                endIndex = midIndex - 1;
            } else {
                System.out.println("search count : " + searchCount);
                return midIndex;
            }
        }
        return ~startIndex;
    }
}

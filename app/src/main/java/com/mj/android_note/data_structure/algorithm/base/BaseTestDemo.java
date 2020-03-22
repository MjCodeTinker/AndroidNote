package com.mj.android_note.data_structure.algorithm.base;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public class BaseTestDemo {


    public static void main(String[] args) {


        BaseAlgorithm baseAlgorithm = new BaseAlgorithm();

        // 冒泡排序
//        int[] count = new int[10000];
//        Random random = new Random();
//        baseAlgorithm.printData(count);
//        System.out.println("bubbling sort ###########");
//
//        for (int i = 0; i < count.length; i++) {
//            count[i] = random.nextInt(10000);
//        }
//
//        baseAlgorithm.printData(baseAlgorithm.bubbling(count));

        // 判断一个数列是否为单调数列
        boolean b = baseAlgorithm.checkArrayIsMonotonous(new int[]{1, 3, 1});
        boolean b1 = baseAlgorithm.checkArrayIsMonotonous(new int[]{1, 2, 3});
        System.out.println("b = " + b + "--b1 = " + b1);
        printLog("##########快速排序开始");
        // 快速排序
        int[] tempQuickSortData = BaseAlgorithm.DATA_QUICK_SORT;
        printLog("原始数据为：" + Arrays.toString(tempQuickSortData));
        baseAlgorithm.quickSort(tempQuickSortData, 0, tempQuickSortData.length - 1);
        printLog("快速排序后的结果为：" + Arrays.toString(tempQuickSortData));
        printLog("##########快速排序结束");

        // 两个数之和等于目标值
        try {
            int[] ints = TwoNumSum.twoSum(new int[]{2, 7, 11, 15}, 9);
            printLog(Arrays.toString(ints));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // 链表中求两个数的和
        TwoNumAdd.print();
    }


    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     * 示例:
     * 给定 nums = [2, 7, 11, 15], target = 9
     * <p>
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     */
    private static class TwoNumSum {
        public static int[] twoSum(int[] nums, int target) throws IllegalAccessException {
            int[] result = new int[2];

            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target) {
                        result[0] = i;
                        result[1] = j;
                        return result;
                    }
                }
            }
            throw new IllegalAccessException("没找到两个数之和");
        }
    }

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 示例：
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */
    private static class TwoNumAdd {
        private static class ListNode {
            int value;
            ListNode next;

            public ListNode(int value) {
                this.value = value;
            }
        }

        public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode headResult = new ListNode(0);
            ListNode m = l1, n = l2, current = headResult;
            int outOfNum = 0;
            while (m != null || n != null) {
                int num1 = m != null ? m.value : 0;
                int num2 = n != null ? n.value : 0;
                int sum = outOfNum + num1 + num2;
                outOfNum = sum / 10;
                current.next = new ListNode(sum % 10);
                current = current.next;
                if (m != null) m = m.next;
                if (n != null) n = n.next;
            }
            if (outOfNum > 0) {
                current = new ListNode(outOfNum);
            }
            return headResult.next;
        }

        private static ListNode toListNodeByNum(int num) {
            String s = String.valueOf(num);
            StringBuilder sb = new StringBuilder(s);
            s = sb.reverse().toString();
            ListNode result = new ListNode(Integer.parseInt(String.valueOf(s.charAt(0))));
            ListNode temp = result;
            for (int i = 1; i < s.length(); i++) {
                temp.next = new ListNode(Integer.parseInt(String.valueOf(s.charAt(i))));
                temp = temp.next;
            }
            return result;
        }

        private static int getNumByListNode(ListNode listNode) {
            StringBuilder sb = new StringBuilder();
            ListNode temp = listNode;
            while (temp != null) {
                sb.append(temp.value);
                temp = temp.next;
            }
            return Integer.parseInt(sb.reverse().toString());
        }

        static void print() {
            ListNode l1 = generateListNodeByArray(new int[]{2, 4, 3});
            ListNode l2 = generateListNodeByArray(new int[]{5, 6, 4});
            ListNode node = addTwoNumbers(l1, l2);
            while (node != null) {
                printLog(node.value + "");
                node = node.next;
            }
        }

        private static ListNode generateListNodeByArray(int[] array) {
            ListNode listNode = new ListNode(array[0]);
            ListNode temp = listNode;
            for (int i = 1; i < array.length; i++) {
                temp.next = new ListNode(array[i]);
                temp = temp.next;
            }
            return listNode;
        }

    }


    private static void printLog(String str) {
        System.out.println(str);
    }

}

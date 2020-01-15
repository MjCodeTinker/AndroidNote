package com.mj.android_note.data_structure.algorithm.base;

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

    }


}

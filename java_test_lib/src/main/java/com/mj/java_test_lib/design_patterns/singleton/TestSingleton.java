package com.mj.java_test_lib.design_patterns.singleton;

/**
 * Author      : MJ
 * Date        : 2020-02-20--20:54
 * Email       : miaojian_666@126.com
 * Description :
 */
public class TestSingleton {

    private TestSingleton() {
        System.out.println("被加载");
    }

//    // 饿汉式单利，线程安全
//    private static TestSingleton instance = new TestSingleton();

//    // 懒汉式单利,依托于synchronize 实现线程安全
//    private static volatile TestSingleton instance;

    public static TestSingleton get() {
//        if (instance == null) {
//            synchronized (TestSingleton.class) {
//                if (instance == null) {
//                    instance = new TestSingleton();
//                }
//            }
//        }
//        return instance;
        return Holder.instance;
    }

    // 内部类形式实现单利，类加载的时候，线程安全
    private static class Holder {
        static TestSingleton instance = new TestSingleton();
    }

}

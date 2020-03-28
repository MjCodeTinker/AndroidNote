package com.mj.android_note.thread.thread_pool;

/**
 * Author      : MJ
 * Date        : 2020-03-28--13:56
 * Email       : miaojian_666@126.com
 * Description : 检测一个方法是否是原子性的，并且是线程安全的
 * 结论：一个方法中只做i+j 操作，这个是具有原子性的，要看传入的值是否是共享变量
 * 如果是线程共享变量：则不能保证线程安全
 * 如果传入的值是线程私有变量是线程安全的
 */
public class TestAtomicMethod {
    long sum(long i, long j) {
        return i + j;
    }
}

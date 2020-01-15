package com.mj.android_note.thread.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {


    public static void main(String[] args) {

        // 野线程
        // new thread 的弊端：
        // 1. 每次开启线程，都new thread 性能开销大
        // 2. 没有统一管理，可能无限制的new thread ，相互之间竞争，导致内存过载，导致oom
        // 3. 缺乏更多功能，如定时执行，定期执行
        Thread thread = new Thread(() -> {
            // TODO: 2020/1/15 野线程，中做一些事情
            printLog("currentThread :" +
                    " name = " + Thread.currentThread().getName()
                    + "--id = " + Thread.currentThread().getId()
                    + "--state = " + Thread.currentThread().getState());
        });
        thread.start();

        // 相比new Thread ，Java 提供了四种线程池,好处在于：
        // 1. 重用线程，减少对象的创建、销毁的开销，性能更佳
        // 2. 可有效控制最大并发线程数，提高系统资源的使用率，避免过多资源竞争，避免阻塞
        // 3. 提供定时执行，定期执行，单线程，并发控制等功能

        // Java 通过Executors 提供了四种线程池,都是通过ThreadPoolExecutor实现的
        // ThreadPoolExecutor 的继承关系： ThreadPoolExecutor -> AbstractServiceExecutor -> ServiceExecutor (interface) -> Executor (interface)

        // 缓存线程池，如果执行的任务大于当前线程数，则会创建新的线程，如果当前线程数大于当前的任务数，则在规定时间内会销毁线程
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 创建一个固定数量的线程池，如果任务数超出最大线程数，则排队等待
        ExecutorService executorService1 = Executors.newFixedThreadPool(10);
        // 一个无数量限制的线程池，任务数如果大于线程数，则创建新的线程
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        // 创建一个单线程的线程池，遵循FIFO,先进先出原则，如果任务数大于线程数，则在队列中等待，适用于数据库操作，文件操作，不能多线程访问，且阻塞UI线程的操作
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();


        // 线程池的工作原理:
        // 依据参数，就可以看出线程池的工作原理
        // threadPolExecutor 各个参数的意义：
        // 1. 如果正在运行的任务数小于 corePoolSize, 会立即创建线程运行这个任务。
        // 2. 如果正在运行的任务数大于等于 corePoolSize ，则会加入到阻塞队列中。
        // 3. 如果队列满了，而且正在运行的任务数小于 maximumPoolSize,则继续创建线程并且运行这个任务。
        // 4. 如果队列满了，而且运行的任务数大于等于 maximumPoolSize,则会抛出异常（依据设置的策略而定 RejectedExecutionHandler）
        // keepAliveTime ： 当其中的线程执行完任务之后，如果没有任务，则会判断当前线程数是否大于corePoolSize，如果大于，则销毁线程，直到最终线程数缩小到 corePoolSize
        // TimeUnit ： keepAliveTime 的时间单位
        // BlockingQueue<Runnable> ： 阻塞队列
        // ThreadFactory : 线程创建器，可以指定线程名，或者线程id等信息
        // RejectedExecutionHandler : 当任务数大于maximumPoolSize ,并且队列中的任务满了之后，则会触发，此策略接口

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1,
                1,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());


    }


    private static void printLog(String msg) {
        System.out.println("###" + msg);
    }


}

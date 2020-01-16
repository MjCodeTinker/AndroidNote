package com.mj.android_note.thread.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

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

        executorService.shutdown();

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

        //########## 线程池中的锁 #########

        // 多线程做i++操作
        MyEntity myEntity = new MyEntity();
        for (int i = 0; i < 10; i++) {
            new Thread(myEntity::addForSynchronized).start();
        }
        try {
            Thread.sleep(2000);
            printLog("num : " + myEntity.getNum());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 为什么多线程处理i++ 操作，不加锁保护，会出现最终只不符合我们的预期
        // 因为：
        // 1. java内存模型中，各个线程会将共享变量从主存中拷贝到工作内存中
        // 2. 做i++操作的时候是基于工作内存中的变量来做的修改，比如当前线程中 i 为999 当时另一个线程已经将i 修改为1000，但是当前线程还是按照999做i++ 的操作
        // 工作内存修改之后，合适写入到主内存中？
        // 1. 这个时机对于普通变量是没有规定的，而针对volatile 修饰的变量给java虚拟机特殊的约定
        // 2. 线程对volatile变量修改之后会立刻被其他线程锁感知，即不会出现脏数据的现象，从而保证数据的“可见性”

        //################ 关键字 volatile ###################
        // 工作内存修改之后，何时写入到主内存中？
        // 1. 这个时机对于普通变量是没有规定的，而针对volatile 修饰的变量给java虚拟机特殊的约定
        // 2. 线程对volatile变量修改之后会立刻被其他线程锁感知，即不会出现脏数据的现象，从而保证数据的“可见性”

        // volatile 是如何保证数据可见性的？
        // 1.生成汇编的时候，会对volatile修饰的共享变量进行写操作的时候会多出Lock前缀的指令
        // 2.Lock前缀指令会引起处理器缓存写回到主存
        // 3.处理器的缓存写回到主存中，会导致其他处理器中的缓存失效
        // 4.其他处理器发现缓存失效，会在去主存中读取数据，即可以获取当前的最新值，这样就能保证每个线程都能获得该变量的最新值

        // 线程有几种状态，分别是什么条件下触发
        //

        //sleep 、wait 、join、yield,interrupt

        // wait
        //interrupt
        //interrupt 可能会导致死锁，比如一个线程中正在用lock,此时如果做中断操作，锁将无法被释放
        //reentrantLock 提供了 tryLock的方法


        // Semaphore

        Thread t = new Thread(){
            @Override
            public void run() {
                super.run();
            }
        };
        t.interrupt();
        Object b = new Object();
        try {
            b.wait();
            b.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    // 多线程做 i++ 操作 实现的三种方式
    private static class MyEntity {
        private volatile int num = 0;

        private AtomicInteger atomicNum = new AtomicInteger(0);

        private volatile ReentrantLock reentrantLock;

        public MyEntity() {
            this.reentrantLock = new ReentrantLock(false);
        }

        // 锁住方法，一个线程一个线程调用
        synchronized void addForSynchronized() {
            for (int i = 0; i < 10000; i++) {
                num++;
            }
            printLog("ThreadName : " + Thread.currentThread().getName() + "---num=" + num);
        }

        // 通过可重入锁，来做线多线程同步的操作
        void addForReentrantLock() {

            for (int i = 0; i < 10000; i++) {
                reentrantLock.lock();
                try {
                    num++;
                }finally {
                    reentrantLock.unlock();
                }
            }
            printLog("ThreadName : " + Thread.currentThread().getName() + "---num=" + num);
        }


        // 通过原子性AtomicInteger来完成
        // 原理是：1.是通过unsafe 来实现的同步问题，unsafe是java中获取对象内存地址的类，他的作用就是比较并且替换 （CAS ）
        // 就是在读取的时候，循环等待的操作，检测读取后和写入时候，值是否发生了改变，更新操作在内存中找到value的位置，方便比较
        // volatile 修饰了AtomicInteger中的value，就是保证了在更新操作时，当前线程可以拿到value最新的值。
        void AddForAtomicInteger() {
            for (int i = 0; i < 10000; i++) {
                atomicNum.getAndIncrement();
            }
            printLog("ThreadName : " + Thread.currentThread().getName() + "---atomicNum=" + atomicNum.get());
        }

        private int getNum() {
            return num;
        }
    }

    private static void printLog(String msg) {
        System.out.println("###" + msg);
    }


}

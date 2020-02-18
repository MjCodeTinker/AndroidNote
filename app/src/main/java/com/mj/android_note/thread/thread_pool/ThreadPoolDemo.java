package com.mj.android_note.thread.thread_pool;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
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
            new Thread(myEntity::AddForAtomicInteger).start();
        }
        try {
            Thread.sleep(2000);
            printLog("num : " + myEntity.getNum());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 为什么多线程处理i++ 操作，不加锁保护，会出现最终值不符合我们的预期
        // 因为：
        // 1. java内存模型中，各个线程会将共享变量从主存中拷贝到工作内存中
        // 2. 做i++操作的时候是基于工作内存中的变量来做的修改，比如当前线程中 i 为999 当时另一个线程已经将i 修改为1000，但是当前线程还是按照999做i++ 的操作
        // 工作内存修改之后，什么时候写入到主内存中？
        // 1. 这个时机对于普通变量是没有规定的，而针对volatile 修饰的变量给java虚拟机特殊的约定
        // 2.线程对volatile变量修改之后会立刻被其他线程所感知，即不会出现脏数据的现象，从而保证数据的“可见性

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
        // NEW RUNNABLE BLOCKED WAITING TIMED_WAITING TERMINATED 六中状态

        //sleep 、wait 、join、yield,interrupt

        // Semaphore
        new MultiThreadMethodVerify();

        // 锁
        new DeadLockReason();

//        // ThreadLocal
//        new TestThreadLocal();

        // java线程工具包 semaphore countdownLatch cyclicBarrier
        new JavaThreadTools();

        // 验证i++ 与 ++ i的区别
        checkIPP();

        // 测试FutureTask
        TestFuture.test();
    }

    private static void checkIPP() {
        int i = 0;
        printLog("i ++ : " + (++i));
        // 打印++i：结果为1， 如果打印i++则打印结果为 0
    }


    // 多线程做 i++ 操作 实现的三种方式
    private static class MyEntity {
        private volatile int num = 0;

        private AtomicInteger atomicNum = new AtomicInteger(0);

        private ReentrantLock reentrantLock;

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
                } finally {
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
            return atomicNum.get();
        }
    }

    private static class MultiThreadMethodVerify {


        MultiThreadMethodVerify() {
//            verifyWait();
//            verifyJoin();
//            verifyYield();
//            verifySleep();
//            verifyInterrupt();
            multiTreadPrintDoubleArray();
        }

        // 验证wait方法
        // - 1.wait 方法一般和 notify 、notifyAll 方法成对出现
        // - 2.当某个线程执行wait方法时，它就进入到一个和该对象相关的等待池中，同时失去了对象锁的功能，使得其他线程可以访问该对象
        // - 3.用户可以通过notify,notifyAll 或设定睡眠时间，来唤醒当前等待池中的线程
        // - 4.wait,notify,notifyAll 方法都必须放在synchronized代码块中，否则会抛出java.lang.IllegalMonitorStateException 异常

        private void verifyWait() {
            printLog("###########verifyWait##########");
            // 对象锁
            Object oWait = new Object();
            // 开启两个线程
            ExecutorService executorService = Executors.newFixedThreadPool(2);

            executorService.execute(() -> {
                synchronized (oWait) {
                    try {
                        printLog("wait:" + Thread.currentThread().getName() + "-开始等待");
                        oWait.wait();
                        printLog("wait:" + Thread.currentThread().getName() + "-执行结束");
                        executorService.shutdown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            executorService.execute(() -> {
                synchronized (oWait) {
                    printLog("wait:" + Thread.currentThread().getName() + "-开始执行");
                    oWait.notifyAll();
                    printLog("wait:" + Thread.currentThread().getName() + "-执行完成");
                }
            });

        }

        // 验证join方法
        // 等待目标线程执行完成之后，其他线程在执行
        private void verifyJoin() {
            printLog("###########verifyJoin##########");
            // 开启两个线程
            Thread t1 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    printLog("join:" + Thread.currentThread().getName() + "-执行");
                }
            };

            Thread t2 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    printLog("join:" + Thread.currentThread().getName() + "-执行");
                }
            };
            try {
                t1.start();
                t1.join();
                t2.start();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printLog("join:" + Thread.currentThread().getName() + "-主线程执行结束");

        }

        // 验证yield
        // 线程礼让，等待其他线程执行完成，在执行自己
        private void verifyYield() {
            printLog("###########verifyYield##########");
            Thread t1 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    printLog("yield:" + Thread.currentThread().getName() + "-开始执行");
                    yield();
                    printLog("yield:" + Thread.currentThread().getName() + "-执行结束");
                }
            };
            Thread t2 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    printLog("yield:" + Thread.currentThread().getName() + "-开始执行");
                    yield();
                    printLog("yield:" + Thread.currentThread().getName() + "-执行结束");
                }
            };

            t1.start();
            t2.start();
            printLog("yield:" + Thread.currentThread().getName() + "-主线程执行结束");
        }


        // 验证sleep
        // sleep在同步代码块中，进入休眠状态，并不会释放synchronized的锁
        private void verifySleep() {
            Object oSleep = new Object();
            ExecutorService service = Executors.newFixedThreadPool(2);
            service.execute(() -> {
                synchronized (oSleep) {
                    try {
                        printLog("sleep:" + Thread.currentThread().getName() + "-开始执行");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        printLog("sleep:" + Thread.currentThread().getName() + "-执行结束");
                    }
                }
            });
            service.execute(() -> {
                synchronized (oSleep) {
                    printLog("sleep:" + Thread.currentThread().getName() + "-执行");
                    if (!service.isShutdown()) {
                        service.shutdown();
                    }
                }
            });
        }

        // 验证interrupt
        // - 1.interrupt 并不会关闭线程，只是在线程中修改一个标志位，暂停线程的执行
        // - 2.interrupt （中断），interrupted (中断结束)，isInterrupt (判断是否被中断)
        private void verifyInterrupt() {
            Thread t2 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    if (isInterrupted()) {
                        printLog("interrupt:" + Thread.currentThread().getName() + "-线程被中断了...");
                    } else {
                        printLog("interrupt:" + Thread.currentThread().getName() + "-中断结束 继续执行");
                    }
                }
            };

            Thread t1 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    printLog("interrupt:" + Thread.currentThread().getName() + "-开始执行");
                    try {
                        sleep(2000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    printLog("interrupt:" + Thread.currentThread().getName() + "-执行结束");
                }
            };
            t2.start();
            t2.interrupt();
            t1.start();
        }

        String[] numArray = {"1", "2", "3", "4"};
        String[] charArray = {"A", "B", "C", "D"};

        // 要求：两个线程分别打印各自数组，打印结果顺序为，1,A,2,B,3,C,4,D
        // 1. 通过wait notify 来实现。notify notifyAll只会通知其他线程等待结束，所以可以完成此问题
        private void multiTreadPrintDoubleArray() {
            Object o = new Object();
            Thread t1 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    // 1.第1中方式，通过wait  和notify 的形式
                    for (int i = 0; i < numArray.length; i++) {
                        printLog(numArray[i]);
                        try {
                            synchronized (o) {
                                o.wait();
                                o.notify();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Thread t2 = new Thread() {
                @Override
                public void run() {
                    super.run();
                    // 1.第1中方式，通过wait  和notify 的形式
                    for (int i = 0; i < charArray.length; i++) {
                        printLog(charArray[i]);
                        synchronized (o) {
                            try {
                                o.notify();
                                o.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            };
            t1.start();
            t2.start();

        }

    }


    // 锁顺序导致的死锁
    private static class DeadLockReason {
        private final Object leftLock = new Object();
        private final Object rightLock = new Object();

        public void leftRight() {
            // 得到leftLock
            synchronized (leftLock) {
                // 得到rightLock锁
                synchronized (rightLock) {
                    // TODO: 2020/1/19
                }
            }
        }

        public void rightLeft() {
            // 得到rightLock
            synchronized (rightLock) {
                //得到leftLock
                synchronized (leftLock) {
                    // TODO: 2020/1/19
                }
            }
        }
    }


    // threadLocal
    private static class TestThreadLocal {

        // 系统的ThreadLocal 来实现
//        private ThreadLocal<String> threadLocal = new ThreadLocal<>();
        private MockThreadLocal<String> threadLocal = new MockThreadLocal<>();

        TestThreadLocal() {
            test();
        }

        private void test() {

            // 两个线程中的数据互相隔离
            ExecutorService executorService = Executors.newFixedThreadPool(3);
            executorService.execute(() -> {
                threadLocal.set("1");
                printLog("threadLocal : + " + Thread.currentThread().getName() + " str = " + threadLocal.get());
            });
            executorService.execute(() -> {
                threadLocal.set("2");
                printLog("threadLocal : + " + Thread.currentThread().getName() + " str = " + threadLocal.get());
            });

            executorService.execute(() -> printLog("threadLocal : + " + Thread.currentThread().getName() + " str = " + threadLocal.get()));
        }

    }

    // 手动实现ThreadLocal
    private static class MockThreadLocal<T> {

        private ConcurrentHashMap<Thread, T> mockThreadLocalTMap = new ConcurrentHashMap<>();

        T get() {
            return mockThreadLocalTMap.get(Thread.currentThread());
        }

        void set(T t) {
            Thread thread = Thread.currentThread();
            mockThreadLocalTMap.put(thread, t);
        }
    }

    // java线程工具包
    private static class JavaThreadTools {

        JavaThreadTools() {
//            eatBreakfast();
//            placeTheOrder();
            running100Meters();
        }

        // 但是只有三个凳子
        Semaphore semaphore = new Semaphore(3);

        // 吃早餐，用信号量来实现
        void eatBreakfast() {
            ExecutorService executorService = Executors.newCachedThreadPool();
            // 10个人要吃早餐
            int COUNT = 10;
            for (int i = 0; i < COUNT; i++) {
                executorService.execute(() -> {
                    try {
                        semaphore.acquire();
                        printLog(Thread.currentThread().getName() + "---正在吃饭中...");
                        // 假设每个人吃饭需要 1秒钟
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        printLog(Thread.currentThread().getName() + "---吃完了...");
                        semaphore.release();
                    }
                });
            }
        }

        // 栅栏
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        // 下单过程，通过 CyclicBarrier(栅栏来完成)
        void placeTheOrder() {
            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
            AtomicReference<String> userToken = new AtomicReference<>();
            AtomicReference<String> commodityPrice = new AtomicReference<>();
            scheduledExecutorService.execute(() -> {
                try {

                    userToken.set("i am userToken");
                    // 获取用户token
                    cyclicBarrier.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            scheduledExecutorService.execute(() -> {
                try {

                    Thread.sleep(1000);
                    commodityPrice.set("1.25元");
                    // 拿到订单token
                    cyclicBarrier.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            try {
                cyclicBarrier.await();
                printLog("拿到用户token：" + userToken.get() + "--拿到商品价格：" + commodityPrice.get() + "--开始下单...");
                if (!scheduledExecutorService.isShutdown()) {
                    scheduledExecutorService.shutdown();
                }
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        CountDownLatch startEndLatch = new CountDownLatch(1);
        CountDownLatch runnerLatch = new CountDownLatch(3);

        // 百米跑步，来表示闭锁的案例
        void running100Meters() {
            ExecutorService executorService = Executors.newFixedThreadPool(3);
            // 3个运动员
            for (int i = 0; i < 3; i++) {
                executorService.execute(() -> {
                    try {
                        // 所有运动员，跑前都需要就位，听枪声
                        startEndLatch.await();
                        long startTime = System.currentTimeMillis();
                        // 假设1秒钟跑完
                        Thread.sleep(2000);
                        printLog(Thread.currentThread().getName() + "---已经冲过终点..." + "--用时：" + (System.currentTimeMillis() - startTime));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        runnerLatch.countDown();
                    }
                });
            }

            try {
                // 教练发话了，开跑
                startEndLatch.countDown();
                //直到所有的人都冲过终点，比赛才结束
                runnerLatch.await();
                printLog("100米比赛结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (!executorService.isShutdown()) {
                    executorService.shutdown();
                }
            }
        }

    }


    private static class TestFuture {

        private static ExecutorService executors = Executors.newSingleThreadExecutor();

        static void test() {
            // Runnable
            executors.execute(() -> {
                printLog("我是Runnable");
            });

            Callable<String> callable = () -> {
                Thread.sleep(1000);
                return "我是Callable";
            };

            FutureTask<String> future = new FutureTask<>(callable);
            executors.execute(future);
            try {
                future.cancel(true);
                String s;
                if(future.isCancelled() || future.isDone()){
                    s = "已经被取消了";
                }else{
                    s = future.get();
                }
                printLog("我是FutureTask s : " + s);
                executors.shutdown();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    private static void printLog(String msg) {
        System.out.println("###" + msg);
    }


}

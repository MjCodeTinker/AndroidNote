package com.mj.lib.base.thread;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author      : MJ
 * Date        : 2018/9/30--14:02
 * Email       : miaojian@conew.com
 * Description : 所有线程相关的工具类
 */

public class ThreadUtils {
    public static ThreadUtils getInstance() {
        return Holder.threadUtils;
    }

    private static class Holder {
        static ThreadUtils threadUtils = new ThreadUtils();
    }

    // 主线程的 handler
    private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    // 处理数据库的单线程池
    private ExecutorService dbExecutor = Executors.newSingleThreadExecutor();

    /**
     * @return 是否运行在主线程
     */
    public boolean isMainThread() {
        return Looper.myLooper() == mainThreadHandler.getLooper();
    }

    /**
     * post到Ui线程
     *
     * @param r ui线程中执行的任务
     */
    public void postUiThread(Runnable r) {
        if (isMainThread()) {
            r.run();
        } else {
            mainThreadHandler.post(r);
        }
    }

    /**
     * 执行db操作
     *
     * @param r 任务
     */
    public void exeDbAction(Runnable r) {
        if (!isMainThread()) {
            r.run();
        } else {
            dbExecutor.execute(r);
        }
    }


}

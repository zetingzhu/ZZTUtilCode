package com.zzt.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author: zeting
 * @date: 2021/4/25
 * 定时线程池管理类
 */
public class ScheduledExecutorManager {

    private static volatile ScheduledExecutorManager instance;

    public static ScheduledExecutorManager getInstance() {
        if (instance == null) {
            synchronized (ScheduledExecutorManager.class) {
                if (instance == null) {
                    instance = new ScheduledExecutorManager();
                }
            }
        }
        return instance;
    }

    /**
     * 定时服务
     */
    private ScheduledExecutorService mExecutorService;
    /**
     * 保存定时任务
     */
    private ScheduledFuture<?> taskRunnable;

    /**
     * 创建一个核心线程
     */
    public ScheduledExecutorManager() {
        startExecutorService();
    }


    /**
     * 两次执行任务的起始时间规律相同,如果任务没有结束会延迟任务结束再次执行
     *
     * @param command      the task to execute
     * @param initialDelay the time to delay first execution
     * @param period       the period between successive executions
     * @param unit         the time unit of the initialDelay and period parameters
     *                     在 initialDelay 后开始执行，然后在 initialDelay+period 后执行，接着在 initialDelay + 2 * period 后执行，依此类推。
     *                     意思是下一次执行任务的时间与任务执行过程花费的时间无关，只与period有关！
     *                     如果此任务的任何一个执行要花费比其周期更长的时间，则将推迟后续执行，但不会同时执行。
     *                     如果任务的任何一个执行遇到异常，则后续执行都会被取消。否则，只能通过执行程序的取消或终止方法来终止该任务。
     */
    public void scheduleAtFixedRate(Runnable command,
                                    long initialDelay,
                                    long period,
                                    TimeUnit unit) {
        hasExecutorRunnable();
        startExecutorService();
        if (mExecutorService != null) {
            taskRunnable = mExecutorService.scheduleAtFixedRate(command, initialDelay, period, unit);
        }
    }

    /**
     * 在每一次执行终止和下一次执行开始之间都存在给定的延迟。
     * 前后两次任务执行的时间间隔相同
     *
     * @param command      the task to execute
     * @param initialDelay the time to delay first execution
     * @param period       the period between successive executions
     * @param unit         the time unit of the initialDelay and period parameters
     */
    public void scheduleWithFixedDelay(Runnable command,
                                       long initialDelay,
                                       long period,
                                       TimeUnit unit) {
        hasExecutorRunnable();
        startExecutorService();
        if (mExecutorService != null) {
            taskRunnable = mExecutorService.scheduleWithFixedDelay(command, initialDelay, period, unit);
        }
    }

    /**
     * 创建线程池
     */
    private void startExecutorService() {
        if (scheduledIsTerminated()) {
            mExecutorService = Executors.newSingleThreadScheduledExecutor();
        }
    }

    /**
     * 判断线程池当前执行线程状态
     *
     * @return true  线程池已关闭 false 线程池没有关闭
     */
    private boolean scheduledIsTerminated() {
        if (mExecutorService == null) {
            return true;
        } else {
            /***
             * Returns:
             * true if all tasks have completed following shut down
             */
            return mExecutorService.isTerminated();
        }
    }

    /**
     * 停止线程池
     */
    public void stopExecutorService() {
        if (taskRunnable != null) {
            if (!taskRunnable.isCancelled()) {
                taskRunnable.cancel(true);
            }
        }
        if (mExecutorService != null) {
            try {
                mExecutorService.shutdown();
                if (!mExecutorService.awaitTermination(3 * 1000, TimeUnit.MILLISECONDS)) {
                    mExecutorService.shutdownNow();
                    mExecutorService = null;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 检查是否有定时在执行，有就关掉
     */
    public void hasExecutorRunnable() {
        if (taskRunnable != null) {
            if (!taskRunnable.isCancelled()) {
                taskRunnable.cancel(true);
            }
        }
    }


}

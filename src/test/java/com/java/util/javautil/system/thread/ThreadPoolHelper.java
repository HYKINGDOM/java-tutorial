package com.java.util.javautil.system.thread;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ThreadPoolHelper {
    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolHelper.class);

    private static final int POOL_SIZE = 40;//线程池大小

    //订单任务线程池
    private static ThreadPoolExecutor comitTaskPool = new ScheduledThreadPoolExecutor(POOL_SIZE,
            new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());


    public static void main(String[] args) {
        MainThread mainThread = new MainThread();

        executeTask(mainThread::test_thread_1_new);
        executeTask(mainThread::test_thread_2_new);
        executeTask(mainThread::test_thread_3_new);
    }






    /**
     * 执行订单任务
     *
     * @param comitTask
     */
    public static void executeTask(Runnable comitTask) {
        comitTaskPool.execute(comitTask);
        logger.debug("【线程池任务】线程池中线程数：" + comitTaskPool.getPoolSize());
        logger.debug("【线程池任务】队列中等待执行的任务数：" + comitTaskPool.getQueue().size());
        logger.debug("【线程池任务】已执行完任务数：" + comitTaskPool.getCompletedTaskCount());
    }


    /**
     * 关闭线程池
     */
    public static void shutdown() {
        logger.debug("shutdown comitTaskPool...");
        comitTaskPool.shutdown();
        try {
            if (!comitTaskPool.isTerminated()) {
                logger.debug("直接关闭失败[" + comitTaskPool.toString() + "]");
                comitTaskPool.awaitTermination(3, TimeUnit.SECONDS);
                if (comitTaskPool.isTerminated()) {
                    logger.debug("成功关闭[" + comitTaskPool.toString() + "]");
                } else {
                    logger.debug("[" + comitTaskPool.toString() + "]关闭失败，执行shutdownNow...");
                    if (comitTaskPool.shutdownNow().size() > 0) {
                        logger.debug("[" + comitTaskPool.toString() + "]没有关闭成功");
                    } else {
                        logger.debug("shutdownNow执行完毕，成功关闭[" + comitTaskPool.toString() + "]");
                    }
                }
            } else {
                logger.debug("成功关闭[" + comitTaskPool.toString() + "]");
            }
        } catch (InterruptedException e) {
            logger.warn("接收到中断请" + comitTaskPool.toString() + "停止操作");
        }
    }
}

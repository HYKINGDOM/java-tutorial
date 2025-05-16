package com.java.tutorial.project.util;

import com.java.tutorial.project.service.AlertService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 高级线程池监控器 支持动态监控、预警和自动调整线程池参数
 *
 */
@Slf4j
@Component
public class ThreadPoolMonitor {

    /**
     * 被监控的线程池映射表
     */
    private final Map<String, MonitoredThreadPool> threadPoolMap = new ConcurrentHashMap<>();

    /**
     * 历史数据，用于趋势分析
     */
    private final Map<String, List<ThreadPoolStats>> historyStats = new ConcurrentHashMap<>();

    @Value("${threadpool.monitor.enabled:true}")
    private boolean monitorEnabled;

    @Value("${threadpool.alert.threshold:80}")
    private int alertThreshold;

    @Value("${threadpool.alert.channels:slack,email}")
    private String alertChannels;

    // 告警服务
    private final AlertService alertService;

    public ThreadPoolMonitor(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostConstruct
    public void init() {
        log.info("线程池监控服务启动，预警阈值: {}%, 告警通道: {}", alertThreshold, alertChannels);
    }

    /**
     * 注册线程池到监控系统
     *
     * @param name     线程池名称
     * @param executor 线程池对象
     * @return 包装后的线程池对象
     */
    public <T extends ThreadPoolExecutor> ThreadPoolExecutor register(String name, T executor) {
        if (!monitorEnabled) {
            return executor;
        }

        MonitoredThreadPool monitoredPool = new MonitoredThreadPool(name, executor);
        threadPoolMap.put(name, monitoredPool);
        historyStats.put(name, new ArrayList<>());

        log.info("线程池 [{}] 已注册到监控系统, 核心线程数: {}, 最大线程数: {}, 队列类型: {}, 队列容量: {}", name,
            executor.getCorePoolSize(), executor.getMaximumPoolSize(), executor.getQueue().getClass().getSimpleName(),
            getQueueCapacity(executor.getQueue()));

        return monitoredPool;
    }

    /**
     * 定时收集线程池统计数据
     */
    @Scheduled(fixedRate = 10000) // 每10秒执行一次
    public void collectStats() {
        if (!monitorEnabled || threadPoolMap.isEmpty()) {
            return;
        }

        threadPoolMap.forEach((name, pool) -> {
            ThreadPoolStats stats = new ThreadPoolStats();
            stats.setTimestamp(System.currentTimeMillis());
            stats.setPoolName(name);
            stats.setActiveThreads(pool.getActiveCount());
            stats.setCorePoolSize(pool.getCorePoolSize());
            stats.setMaximumPoolSize(pool.getMaximumPoolSize());
            stats.setLargestPoolSize(pool.getLargestPoolSize());
            stats.setPoolSize(pool.getPoolSize());
            stats.setQueueSize(pool.getQueue().size());
            stats.setQueueCapacity(getQueueCapacity(pool.getQueue()));
            stats.setTaskCount(pool.getTaskCount());
            stats.setCompletedTaskCount(pool.getCompletedTaskCount());
            stats.setRejectedCount(pool.getRejectedCount());

            // 计算指标
            calculateMetrics(stats);

            // 存储历史数据
            List<ThreadPoolStats> history = historyStats.get(name);
            history.add(stats);

            // 只保留最近50条记录
            if (history.size() > 50) {
                history.remove(0);
            }

            // 检查是否需要告警
            checkAlert(stats);

            // 检查是否需要自动调整线程池参数
            if (stats.getActiveThreadRatio() > 90 && stats.getQueueUsageRatio() > 70) {
                autoAdjustThreadPool(pool);
            }

            log.debug("线程池 [{}] 状态: 活跃线程 {}/{} ({}%), 队列 {}/{} ({}%), 已完成任务 {}, 拒绝任务 {}", name,
                stats.getActiveThreads(), stats.getMaximumPoolSize(), stats.getActiveThreadRatio(),
                stats.getQueueSize(), stats.getQueueCapacity(), stats.getQueueUsageRatio(),
                stats.getCompletedTaskCount(), stats.getRejectedCount());
        });
    }

    /**
     * 获取线程池使用趋势分析
     *
     * @param poolName 线程池名称
     * @return 趋势数据
     */
    public ThreadPoolTrend getTrend(String poolName) {
        List<ThreadPoolStats> history = historyStats.getOrDefault(poolName, Collections.emptyList());
        if (history.isEmpty()) {
            return null;
        }

        ThreadPoolTrend trend = new ThreadPoolTrend();
        trend.setPoolName(poolName);

        // 计算平均活跃线程比例趋势
        trend.setAvgActiveThreadRatio(
            history.stream().mapToDouble(ThreadPoolStats::getActiveThreadRatio).average().orElse(0));

        // 计算平均队列使用率趋势
        trend.setAvgQueueUsageRatio(
            history.stream().mapToDouble(ThreadPoolStats::getQueueUsageRatio).average().orElse(0));

        // 计算拒绝任务数趋势
        trend.setTotalRejectedCount(history.stream().mapToLong(ThreadPoolStats::getRejectedCount).sum());

        // 计算任务完成率趋势
        trend.setTaskCompletionRatio(
            history.stream().mapToDouble(ThreadPoolStats::getTaskCompletionRatio).average().orElse(100));

        return trend;
    }

    /**
     * 获取所有线程池状态快照
     *
     * @return 线程池状态列表
     */
    public List<ThreadPoolStats> getAllPoolStats() {
        return threadPoolMap.keySet().stream().map(name -> {
            List<ThreadPoolStats> history = historyStats.getOrDefault(name, Collections.emptyList());
            return history.isEmpty() ? null : history.get(history.size() - 1);
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 计算线程池指标
     */
    private void calculateMetrics(ThreadPoolStats stats) {
        // 活跃线程比例
        stats.setActiveThreadRatio(
            stats.getMaximumPoolSize() > 0 ? (double)stats.getActiveThreads() / stats.getMaximumPoolSize() * 100 : 0);

        // 队列使用率
        stats.setQueueUsageRatio(
            stats.getQueueCapacity() > 0 ? (double)stats.getQueueSize() / stats.getQueueCapacity() * 100 : 0);

        // 任务完成率
        stats.setTaskCompletionRatio(
            stats.getTaskCount() > 0 ? (double)stats.getCompletedTaskCount() / stats.getTaskCount() * 100 : 100);
    }

    /**
     * 检查是否需要发送告警
     */
    private void checkAlert(ThreadPoolStats stats) {
        boolean needAlert = false;
        StringBuilder alertMsg = new StringBuilder();

        // 检查活跃线程比例
        if (stats.getActiveThreadRatio() > alertThreshold) {
            needAlert = true;
            alertMsg.append("活跃线程比例过高: ").append(String.format("%.1f%%", stats.getActiveThreadRatio()))
                .append("; ");
        }

        // 检查队列使用率
        if (stats.getQueueUsageRatio() > alertThreshold) {
            needAlert = true;
            alertMsg.append("队列使用率过高: ").append(String.format("%.1f%%", stats.getQueueUsageRatio()))
                .append("; ");
        }

        // 检查拒绝任务数
        if (stats.getRejectedCount() > 0) {
            needAlert = true;
            alertMsg.append("存在任务被拒绝: ").append(stats.getRejectedCount()).append("个; ");
        }

        if (needAlert) {
            String finalMsg = String.format("线程池告警 [%s]: %s", stats.getPoolName(), alertMsg);
            alertService.sendAlert(finalMsg, alertChannels.split(","));
        }
    }

    /**
     * 自动调整线程池参数
     */
    private void autoAdjustThreadPool(ThreadPoolExecutor executor) {
        int currentMax = executor.getMaximumPoolSize();
        int newMax = Math.min(currentMax + 5, currentMax * 2); // 最多翻倍

        log.info("自动调整线程池 [{}] 最大线程数: {} -> {}", getPoolName(executor), currentMax, newMax);

        executor.setMaximumPoolSize(newMax);
    }

    /**
     * 获取线程池名称
     */
    private String getPoolName(ThreadPoolExecutor executor) {
        for (Map.Entry<String, MonitoredThreadPool> entry : threadPoolMap.entrySet()) {
            if (entry.getValue().getOriginalExecutor() == executor) {
                return entry.getKey();
            }
        }
        return "unknown";
    }

    /**
     * 获取队列容量
     */
    private int getQueueCapacity(BlockingQueue<?> queue) {
        try {
            if (queue instanceof LinkedBlockingQueue) {
                Field field = LinkedBlockingQueue.class.getDeclaredField("capacity");
                field.setAccessible(true);
                return (int)field.get(queue);
            } else if (queue instanceof ArrayBlockingQueue) {
                Field field = ArrayBlockingQueue.class.getDeclaredField("items");
                field.setAccessible(true);
                Object[] items = (Object[])field.get(queue);
                return items.length;
            }
        } catch (Exception e) {
            log.warn("获取队列容量失败", e);
        }

        return Integer.MAX_VALUE; // 默认为无界队列
    }

    /**
     * 线程池监控统计信息
     */
    @Data
    public static class ThreadPoolStats {
        private long timestamp;
        private String poolName;
        private int activeThreads;
        private int corePoolSize;
        private int maximumPoolSize;
        private int largestPoolSize;
        private int poolSize;
        private int queueSize;
        private int queueCapacity;
        private long taskCount;
        private long completedTaskCount;
        private long rejectedCount;

        // 计算的指标
        private double activeThreadRatio;     // 活跃线程比例
        private double queueUsageRatio;       // 队列使用率
        private double taskCompletionRatio;   // 任务完成率
    }

    /**
     * 线程池趋势分析
     */
    @Data
    public static class ThreadPoolTrend {
        private String poolName;
        private double avgActiveThreadRatio;  // 平均活跃线程比例
        private double avgQueueUsageRatio;    // 平均队列使用率
        private long totalRejectedCount;      // 总拒绝任务数
        private double taskCompletionRatio;   // 任务完成率
    }

    /**
     * 监控包装的线程池 拦截执行和拒绝事件
     */
    public class MonitoredThreadPool extends ThreadPoolExecutor {
        private final String name;
        private final ThreadPoolExecutor originalExecutor;
        private long rejectedCount = 0;

        public MonitoredThreadPool(String name, ThreadPoolExecutor executor) {
            super(executor.getCorePoolSize(), executor.getMaximumPoolSize(),
                executor.getKeepAliveTime(TimeUnit.NANOSECONDS), TimeUnit.NANOSECONDS, executor.getQueue(),
                executor.getThreadFactory(), executor.getRejectedExecutionHandler());

            this.name = name;
            this.originalExecutor = executor;

            setRejectedExecutionHandler(new RejectedHandler(executor.getRejectedExecutionHandler()));
        }

        public ThreadPoolExecutor getOriginalExecutor() {
            return originalExecutor;
        }

        public long getRejectedCount() {
            return rejectedCount;
        }

        @Override
        public void setRejectedExecutionHandler(RejectedExecutionHandler handler) {
            // 使用反射或直接继承方式设置
            try {
                Field field = ThreadPoolExecutor.class.getDeclaredField("handler");
                field.setAccessible(true);
                field.set(this, handler);
            } catch (Exception e) {
                log.error("设置拒绝处理器失败", e);
            }
        }

        /**
         * 拒绝策略包装器
         */
        private class RejectedHandler implements RejectedExecutionHandler {
            private final RejectedExecutionHandler original;

            public RejectedHandler(RejectedExecutionHandler original) {
                this.original = original;
            }

            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                rejectedCount++;
                log.warn("线程池 [{}] 拒绝任务，当前拒绝总数: {}", name, rejectedCount);
                original.rejectedExecution(r, executor);
            }
        }
    }
}


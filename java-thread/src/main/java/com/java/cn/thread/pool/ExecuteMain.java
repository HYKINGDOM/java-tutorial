package com.java.cn.thread.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class ExecuteMain {
    public static void main(String[] args) throws Exception {
        // 创建配置对象
        GenericObjectPoolConfig<ComplexObject> poolConfig = new GenericObjectPoolConfig<>();
        // 最大空闲实例数，空闲超过此值将会被销毁淘汰
        poolConfig.setMaxIdle(5);
        // 最大对象数量，包含借出去的和空闲的
        poolConfig.setMaxTotal(20);
        // 最小空闲实例数，对象池将至少保留2个空闲对象
        poolConfig.setMinIdle(2);
        // 对象池满了，是否阻塞获取（false则借不到直接抛异常）
        poolConfig.setBlockWhenExhausted(true);
        // BlockWhenExhausted为true时生效，对象池满了阻塞获取超时，不设置则阻塞获取不超时，也可在borrowObject方法传递第二个参数指定本次的超时时间
        poolConfig.setMaxWaitMillis(3000);
        // 创建对象后是否验证对象，调用objectFactory#validateObject
        poolConfig.setTestOnCreate(false);
        // 借用对象后是否验证对象 validateObject
        poolConfig.setTestOnBorrow(true);
        // 归还对象后是否验证对象 validateObject
        poolConfig.setTestOnReturn(true);
        // 每30秒定时检查淘汰多余的对象, 启用单独的线程处理
        poolConfig.setTimeBetweenEvictionRunsMillis(1000 * 60 * 30);
        // 每30秒定时检查期间是否验证对象 validateObject
        poolConfig.setTestWhileIdle(false);
        // jmx监控，和springboot自带的jmx冲突，可以选择关闭此配置或关闭springboot的jmx配置
        poolConfig.setJmxEnabled(false);

        ComplexPooledObjectFactory objectFactory = new ComplexPooledObjectFactory();
        GenericObjectPool<ComplexObject> objectPool = new GenericObjectPool<>(objectFactory, poolConfig);
        // 申请对象
        ComplexObject obj1 = objectPool.borrowObject();
        System.out.println("第一次申请对象：" + obj1.getName());
        // returnObject应该放在finally中 避免业务异常没有归还对象，demo仅做示例
        objectPool.returnObject(obj1);
        // 申请对象， 由于之前归还了，借用的还是之前的对象
        ComplexObject obj2 = objectPool.borrowObject();
        System.out.println("第二次申请对象：" + obj2.getName());
        // 再次申请对象，由于之前没有归还，借用的是新创建的
        ComplexObject obj3 = objectPool.borrowObject();
        System.out.println("第三次申请对象：" + obj3.getName());

        // returnObject应该放在finally中 避免业务异常没有归还对象，demo仅做示例
        objectPool.returnObject(obj2);
        objectPool.returnObject(obj3);
    }
}

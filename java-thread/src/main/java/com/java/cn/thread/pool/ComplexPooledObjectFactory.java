package com.java.cn.thread.pool;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.util.concurrent.ThreadLocalRandom;

public class ComplexPooledObjectFactory implements PooledObjectFactory<ComplexObject> {

    @Override
    public PooledObject<ComplexObject> makeObject() {
        // 随机指定一个名称，用于区分ComplexObject并使用默认池化对象包装ComplexObject
        String name = "test" + ThreadLocalRandom.current().nextInt(100);
        return new DefaultPooledObject<>(new ComplexObject(name));
    }

    @Override
    public void destroyObject(PooledObject<ComplexObject> p) {
        // 销毁对象，当清空，空闲对象大于配置值等会销毁多余对象
        // 此处应释放掉对象占用的资源，如关闭连接，关闭IO等
    }

    @Override
    public boolean validateObject(PooledObject<ComplexObject> p) {
        // 验证对象状态是否正常，是否可用
        return true;
    }

    @Override
    public void activateObject(PooledObject<ComplexObject> p) {
        // 激活对象，使其可用
    }

    @Override
    public void passivateObject(PooledObject<ComplexObject> p) {
        // 钝化对象，使其不可用
    }
}

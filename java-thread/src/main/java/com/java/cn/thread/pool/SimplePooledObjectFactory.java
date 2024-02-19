package com.java.cn.thread.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.util.concurrent.ThreadLocalRandom;

public class SimplePooledObjectFactory extends BasePooledObjectFactory<ComplexObject> {
    @Override
    public ComplexObject create() {
        // 随机指定一个名称，用于区分ComplexObject
        String name = "test" + ThreadLocalRandom.current().nextInt(100);
        return new ComplexObject(name);
    }

    @Override
    public PooledObject<ComplexObject> wrap(ComplexObject obj) {
        // 使用默认池化对象包装ComplexObject
        return new DefaultPooledObject(obj);
    }
}

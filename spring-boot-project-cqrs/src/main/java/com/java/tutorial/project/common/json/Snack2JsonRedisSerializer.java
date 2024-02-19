package com.java.tutorial.project.common.json;

import org.noear.snack.ONode;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * 自定义Redis序列化
 */
public final class Snack2JsonRedisSerializer implements RedisSerializer<Object> {

    @Override
    public byte[] serialize(Object obj) throws SerializationException {
        return ONode.serialize(obj).getBytes();
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return (bytes == null ? null : ONode.deserialize(new String(bytes)));
    }

}

package com.dngzs.serialization.protobuf;

import com.dngzs.chat.serialization.Serializer;

/**
 * ProtoStuff序列化
 *
 * @author zhangbo
 */
public class ProtoStuffSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return 0;
    }

    @Override
    public byte[] serialize(Object object) {
        return new byte[0];
    }

    @Override
    public Object deserialize(Class aClass, byte[] bytes) {
        return null;
    }
}

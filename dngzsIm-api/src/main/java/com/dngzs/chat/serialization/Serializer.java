package com.dngzs.chat.serialization;

/**
 * 序列化接口
 *
 * @author zhangbo
 */
public interface Serializer<T> {

    /**
     * 序列化算法
     *
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * 序列化
     *
     * @param object
     * @return
     */
    byte[] serialize(Object object);


    /**
     * 反序列化
     *
     * @param tClass
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> tClass, byte[] bytes);

}

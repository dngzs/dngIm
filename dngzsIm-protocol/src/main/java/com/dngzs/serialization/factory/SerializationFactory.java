package com.dngzs.serialization.factory;


import com.dngzs.chat.serialization.Serializer;
import com.dngzs.serialization.SerializationEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * 采用spi的加载方式，如果想自己定义序列化方式，请自行扩展
 * <p>
 * 序列化工厂
 *
 * @author zhangbo
 */
public class SerializationFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerializationFactory.class);

    private SerializationFactory() {
    }

    private static Map<Byte, Serializer> serializerMap = new HashMap(16);

    static {
        ServiceLoader<Serializer> load = ServiceLoader.load(Serializer.class, Thread.currentThread().getContextClassLoader());
        load.forEach(serializer -> serializerMap.put(serializer.getSerializerAlgorithm(), serializer));
    }

    /**
     * 获取序列化
     *
     * @param serializerAlgorithm
     * @return
     */
    public static Serializer getSerializer(byte serializerAlgorithm) {
        Serializer serializer = serializerMap.get(serializerAlgorithm);
        if (serializer == null) {
            SerializationEnum type = SerializationEnum.parse(serializerAlgorithm);
            if (type != null) {
                LOGGER.error("Serializer implementation [{}] not found", type.name());
                throw new IllegalArgumentException("Serializer implementation [" + type.name() + "] not found");
            } else {
                LOGGER.error("Unsupported serializer type with code:{} ", serializerAlgorithm);
                throw new IllegalArgumentException("Unsupported serializer type with code: " + serializerAlgorithm);
            }
        }
        return serializer;
    }
}

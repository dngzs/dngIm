package com.dngzs.payload;

import com.dngzs.packet.JProtocolHeader;
import lombok.Data;

/**
 * 序列化载体
 *
 * @author zhangbo
 */
@Data
public class Payload {

    private byte[] bytes;

    private byte serializableCode;

    private JProtocolHeader header;

    /**
     * 时间戳
     */
    private transient long timestamp;

}

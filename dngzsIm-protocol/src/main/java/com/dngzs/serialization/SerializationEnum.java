package com.dngzs.serialization;


/**
 * 序列化枚举类型
 * <p>
 * 位数限制最多支持15种不同的序列化/反序列化方式
 * proto_stuff   = 0x01
 * hessian      = 0x02
 * kryo         = 0x03
 * java         = 0x04
 * ...
 * XX1          = 0x0e
 * XX2          = 0x0f
 *
 * 默认fastsjon
 *
 * @author zhangbo
 */
public enum SerializationEnum {
    /**
     * protostuff
     */
    PROTOSTUFF((byte) 0x01),
    HESSIAN((byte) 0x02),
    JAVA((byte) 0x03),
    KRYO((byte) 0x04),
    FASTJSON((byte) 0x05);

    private byte code;

    SerializationEnum(byte code) {
        this.code = code;
    }


    public static SerializationEnum parse(byte code) {
        SerializationEnum[] values = values();
        for (SerializationEnum value : values) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

    public static SerializationEnum getDefault() {
        return FASTJSON;
    }

    public byte getCode() {
        return code;
    }
}

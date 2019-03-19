package com.dngzs.packet;

/**
 * 协议抽象类
 *
 * @author zhangbo
 */
public class JProtocolHeader {

    /**
     * 协议头长度
     */
    public static final int HEADER_SIZE = 49;

    /**
     * 魔数
     */
    public static final short MAGIC = (short) 0Xbabe;

    /**
     * 版本号
     */
    private int version;

    /**
     * 序列化号
     */
    private byte serializableCode;

    /**
     * 全局状态
     */
    private byte[] sessionId;

    /**
     * 模块号
     */
    private int module;

    /**
     * 业务号
     */
    private int business;

    /**
     * 消息长度
     */
    private int bodySize;




    public byte getSerializableCode() {
        return serializableCode;
    }

    public void setSerializableCode(byte serializableCode) {
        this.serializableCode = serializableCode;
    }

    public byte[] getSessionId() {
        return sessionId;
    }

    public void setSessionId(byte[] sessionId) {
        this.sessionId = sessionId;
    }

    public int getModule() {
        return module;
    }

    public void setModule(int module) {
        this.module = module;
    }

    public int getBusiness() {
        return business;
    }

    public void setBusiness(int business) {
        this.business = business;
    }

    public int getBodySize() {
        return bodySize;
    }

    public void setBodySize(int bodySize) {
        this.bodySize = bodySize;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }



}

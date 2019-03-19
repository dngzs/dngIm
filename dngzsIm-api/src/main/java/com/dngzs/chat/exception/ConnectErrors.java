package com.dngzs.chat.exception;

/**
 * 连接异常
 *
 * @author zhangbo
 */
public abstract class ConnectErrors {

    public static final ConnectException MAGIC_ERROR = new ConnectException("illegal magic");
}

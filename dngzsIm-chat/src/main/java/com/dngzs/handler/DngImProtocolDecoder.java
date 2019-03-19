package com.dngzs.handler;


import com.dngzs.chat.exception.ConnectErrors;
import com.dngzs.chat.exception.ConnectException;
import com.dngzs.packet.JProtocolHeader;
import com.dngzs.payload.Payload;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * 解码器
 *
 * @author zhangbo
 */
public class DngImProtocolDecoder extends ReplayingDecoder<DngImProtocolDecoder.State> {

    public DngImProtocolDecoder() {
        super(State.MAGIC);
    }

    /**
     *  协议头
     */
    private final JProtocolHeader header = new JProtocolHeader();

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        switch (state()){
            case MAGIC:
                //魔数
                checkMagic(byteBuf.readByte());
                checkpoint(State.VERSION);
            case VERSION:
                header.setVersion(byteBuf.readInt());
                checkpoint(State.SERIALIZABLECODE);
            case SERIALIZABLECODE:
                header.setSerializableCode(byteBuf.readByte());
                checkpoint(State.SESSIONID);
            case SESSIONID:
                byte[] bytes = new byte[32];
                byteBuf.readBytes(bytes);
                header.setSessionId(bytes);
                checkpoint(State.MODULE);
            case MODULE:
                header.setModule(byteBuf.readInt());
                checkpoint(State.BUSINESS);
            case BUSINESS:
                header.setBusiness(byteBuf.readInt());
                checkpoint(State.BODYSIZE);
            case BODYSIZE:
                header.setBodySize(byteBuf.readInt());
                checkpoint(State.BODY);
            case BODY:
                byte [] body = new byte[header.getBodySize()];
                byteBuf.readBytes(body);
                Payload payload = new Payload();
                payload.setHeader(header);
                payload.setBytes(body);
                payload.setSerializableCode(header.getSerializableCode());
                payload.setTimestamp(System.currentTimeMillis());
                list.add(payload);
        }

    }

    /**
     * 检查魔数
     *
     * @param magic
     * @throws ConnectException
     */
    private static void checkMagic(short magic) throws ConnectException {
        if (magic != JProtocolHeader.MAGIC) {
            throw  ConnectErrors.MAGIC_ERROR;
        }
    }


    enum State {
        MAGIC,
        VERSION,
        SERIALIZABLECODE,
        SESSIONID,
        MODULE,
        BUSINESS,
        BODYSIZE,
        BODY;
    }
}

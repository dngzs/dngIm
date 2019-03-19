package com.dngzs.handler;

import com.dngzs.packet.JProtocolHeader;
import com.dngzs.payload.Payload;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * <pre>
 *
 *                                          Protocol
 *  ┌ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─┐
 *       2   │   2     │    1   │     32    │      4      │     4      │      4     │
 *  ├ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─
 *           │         │        │           │             │            │            │
 *  │  魔数    version    序列化    Session      模块号         业务号       消息长度      Body Content   │
 *           │         │        │           │             │            │            │
 *  └ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─┘
 *
 * 消息头49个字节定长
 * = 2 // magic = (short) 0xbabe
 * + 2 // 版本号，用来区分版本信息
 * + 1 // 序列化位, 用来区分何种序列化协议
 * + 32 // session_id，全局区分channel
 * + 4 // 模块号，用来区分那个模块下的数据
 * + 4 // 业务号，用来区分那个模块下的业务
 * + 4 // 消息长度 用来表示body的长度值
 * </pre>
 *
 * 编码器
 *
 * @author zhangbo
 */
public class DngImProtocolEncoder extends MessageToByteEncoder<Payload> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Payload payload, ByteBuf out) throws Exception {
        out.writeChar(JProtocolHeader.MAGIC).writeInt(payload.getHeader().getVersion())
                .writeByte(payload.getSerializableCode())
                .writeBytes(payload.getHeader().getSessionId())
                .writeInt(payload.getHeader().getModule())
                .writeInt(payload.getHeader().getBusiness())
                .writeInt(payload.getHeader().getBodySize())
                .writeBytes(payload.getBytes());

    }
}

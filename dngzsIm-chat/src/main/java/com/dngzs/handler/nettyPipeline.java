package com.dngzs.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * netty handler
 *
 * @author zhangbo
 */
public class nettyPipeline extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("dngImProtocolDecoder",new DngImProtocolDecoder());
        pipeline.addLast("dngImProtocolEncoder",new DngImProtocolEncoder());
    }
}

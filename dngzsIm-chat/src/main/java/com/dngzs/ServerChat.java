package com.dngzs;

import com.dngzs.handler.nettyPipline;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class ServerChat implements AutoCloseable{

    private int port;

    private static final Logger LOG = LoggerFactory.getLogger(ServerChat.class);

    private NioEventLoopGroup boss;
    private NioEventLoopGroup worker;

    @Override
    public void close() throws Exception {
        boss.shutdownGracefully();
        boss.shutdownGracefully();
    }

    public ServerChat(int port) {
        this.port = port;
    }

    /**
     * netty 启动
     */
    public void init() {
        try {
            boss = new NioEventLoopGroup(1);
            worker = new NioEventLoopGroup();
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss,worker).channel(NioServerSocketChannel.class).childHandler(new nettyPipline());
            bootstrap.bind(port);
            LOG.info("netty 启动在8888端口");
        }finally {
            try {
                close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

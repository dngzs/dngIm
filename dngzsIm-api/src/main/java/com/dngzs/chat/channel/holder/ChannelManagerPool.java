package com.dngzs.chat.channel.holder;

import java.nio.channels.SocketChannel;

public interface ChannelManagerPool {

    SocketChannel get(Long userId);

    void remove(SocketChannel socketChannel);

    void add(Long userId, SocketChannel socketChannel);

}

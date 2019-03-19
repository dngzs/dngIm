package com.dngzs.holder;

import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 连接持有
 */
public class DefaultChannelManagerPool {

    private static final Map<Long, SocketChannel> map = new ConcurrentHashMap<Long, SocketChannel>();

    /**
     * 获取连接
     *
     * @param userId
     * @return
     */
    private SocketChannel get(Long userId) {
        return map.get(userId);
    }

    /**
     * 移除连接
     *
     * @param socketChannel
     */
    public void remove(SocketChannel socketChannel) {
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() == socketChannel) {
                map.remove(entry.getKey());
            }
        }
    }

    /**
     * 添加连接
     *
     * @param userId
     * @param socketChannel
     */
    public void add(Long userId, SocketChannel socketChannel) {
        map.put(userId, socketChannel);
    }
}

package com.dngzs.core.netty;

import com.dngzs.ServerChat;
import com.dngzs.core.properties.NettyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * spring容器完成事件，用来启动netty
 *
 * @author zhangbo
 */
@Component
public class NettyStartListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private NettyProperties nettyProperties;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ServerChat serverChat = new ServerChat(nettyProperties.getPort());
        serverChat.init();
    }
}

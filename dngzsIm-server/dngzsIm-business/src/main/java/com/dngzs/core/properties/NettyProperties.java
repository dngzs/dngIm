package com.dngzs.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "netty")
@Component
public class NettyProperties {

    private final static int DEFAULT_PORT = 8888;

    private int port = DEFAULT_PORT;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}

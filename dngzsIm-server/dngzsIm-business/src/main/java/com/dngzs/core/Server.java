package com.dngzs.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 启动类
 *
 * @author zhangbo
 */
@SpringBootApplication
@EnableConfigurationProperties
public class Server {
    public static void main(String[] args) {

        SpringApplication springBootApplication = new SpringApplication(Server.class);
        springBootApplication.run(args);
    }
}

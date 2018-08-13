package com.xk.billbook.admin.common.base.model;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@ConfigurationProperties(prefix = "spring.redis")
@PropertySource("classpath:/redis.properties")
public class Redis {

    private String database;
    private String host;
    private String password;
    private int port;
    private int timeout;
    private String maxIdle;
    private String minIdle;
    private String maxActive;
    private String maxWait;


}

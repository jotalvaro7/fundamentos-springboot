package com.fundamentosplatzi.springboot.fundamentos.configuration;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@EnableConfigurationProperties
@PropertySource("classpath:connection.properties")
@ConfigurationProperties(prefix = "connection")
public class DbConnectionCapsule {

    @NotNull
    private String jdbc_url;
    @NotNull
    private String driver;
    @NotNull
    private String username;
    @NotNull
    private String password;

}

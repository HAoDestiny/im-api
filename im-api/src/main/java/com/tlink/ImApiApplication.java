package com.tlink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author destiny
 * @date 2021/6/3 13:03
 */

@EnableConfigurationProperties
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class ImApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImApiApplication.class, args);
    }
}

package com.tlink.service;


import cn.hutool.core.util.StrUtil;
import com.tlink.common.core.exception.BusinessException;
import com.tlink.service.client.IMClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "im")
@EnableConfigurationProperties
public class ImConfig {

    private String url;

    @Bean
    public IMClient initIMClient() {
        if (StrUtil.isBlank(this.url)) {
            throw new BusinessException("IM Client init fail: im.url does not support empty");
        }

        return new IMClient(this.url);
    }
}

package com.example.demo2.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhanghao
 * @date 2021-11-15
 */
//可以使用@PropertySource("classpath:proconf.properties")指定配置文件,但是只能是properties文件不能是yml
@Data
@Component
@ConfigurationProperties(prefix = "server")
public class PropertiesConfig {
    private int port;
}

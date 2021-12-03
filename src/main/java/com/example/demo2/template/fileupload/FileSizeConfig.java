package com.example.demo2.template.fileupload;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * 配置请求size,如果在yml配置了这里就不用了,最好在yml中配置
 * spring:
 *   servlet:
 *     multipart:
 *       max-file-size: 200MB
 *       max-request-size: 200MB
 *
 * @author zhanghao
 * @date 2021-12-02
 */
@Configuration
public class FileSizeConfig {
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxRequestSize(DataSize.parse("200MB"));
        factory.setMaxFileSize(DataSize.parse("200MB"));
        return factory.createMultipartConfig();
    }

}

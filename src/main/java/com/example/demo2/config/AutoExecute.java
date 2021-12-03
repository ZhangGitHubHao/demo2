package com.example.demo2.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author zhanghao
 * @date 2021-11-23
 */
@Component
@Slf4j
public class AutoExecute implements ApplicationRunner {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("服务启动我执行了");
        log.info("info信息");
        log.warn("warn信息");
        log.error("error信息");
        logger.info("info信息");
        logger.warn("warn信息");
        logger.error("error信息");
    }
}

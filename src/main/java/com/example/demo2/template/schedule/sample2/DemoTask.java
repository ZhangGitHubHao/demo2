package com.example.demo2.template.schedule.sample2;

import org.springframework.stereotype.Component;

/**
 * @author zhanghao
 * @date 2021-11-22
 */
@Component("demoTask")
public class DemoTask {

    public void taskWithParams(String param1, Integer param2) {
        System.out.println("这是有参示例任务：" + param1 + param2);
    }

    public void taskNoParams() {
        System.out.println("这是无参示例任务");
    }
}
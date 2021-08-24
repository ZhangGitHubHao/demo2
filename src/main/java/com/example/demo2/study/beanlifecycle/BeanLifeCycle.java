package com.example.demo2.study.beanlifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * BeanLifeCycle
 *
 * @author 860120014
 * @date 2021-08-19
 */
public class BeanLifeCycle {
    public static void main(String[] args) {
        System.out.println("开始初始化容器!!");
        ApplicationContext factory = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println("容器初始化成功!!");
        Person person = factory.getBean("person",Person.class);
        System.out.println(person);
        System.out.println("关闭容器!!");
        ((ClassPathXmlApplicationContext)factory).registerShutdownHook();
    }
}

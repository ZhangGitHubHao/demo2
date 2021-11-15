package com.example.demo2.annotation;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author zhanghao
 * @date 2021-11-15
 */
@Aspect
@Component
@Log4j2
public class ElapsedTimeAop {
    @Pointcut("@annotation(com.example.demo2.annotation.ElapsedTime)")
    public void pointcut() {}

    @Around("pointcut()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info("方法"+className+"."+methodName+"耗时:"+(end-start)+"ms");
        return result;
    }
}

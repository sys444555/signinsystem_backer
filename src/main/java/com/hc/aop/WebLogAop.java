package com.hc.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * @Author: Charles Chan
 * @Date: 2019/3/11 17:10
 * @Version 1.0
 * 统用日志切面类
 */

@Aspect
@Component
public class WebLogAop {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //切点
    @Pointcut("execution(* com.hc.modules.*.service..*.*(..))")

    public void webLogAop(){}

    @Before("webLogAop()")
    public void doBefore(JoinPoint joinPoint){
        logger.info("目标方法：" +joinPoint.getSignature().getDeclaringTypeName()
        +"."+joinPoint.getSignature().getName());
        logger.info("参数为："+ Arrays.toString(joinPoint.getArgs()));
        logger.info("被织入的对象为："+ joinPoint.getTarget());
    }

    @AfterReturning(returning = "ret", pointcut = "webLogAop()")
    public void doAfterReturning(Object ret) throws Throwable {
        logger.info("RESPONSE : " + ret);
    }

}

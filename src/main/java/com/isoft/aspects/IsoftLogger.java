package com.isoft.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class IsoftLogger {

    @Around("pointCut()")
    public static Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String name = joinPoint.getSignature().getName();
        log.info(name + "  started");
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed(); //chon method momkene 1 chizi return kone ke ma bayad on ro return konim ke on value az dast nare
        long finish = System.currentTimeMillis();
        log.info("finished execution " + name + ". time took=" + (finish - start) + "ms");
        return result;
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public static void afterThrowing(JoinPoint joinPoint, Exception exception) {
        log.error("An exception happened in method " + joinPoint.getSignature().getName() + " cause: " + exception.getMessage());
    }

    @Pointcut("execution(* com.isoft.*.*.*(..))")
    public static void pointCut() {
    }

}



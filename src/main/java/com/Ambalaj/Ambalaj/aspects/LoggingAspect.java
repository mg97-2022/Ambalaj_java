//package com.Ambalaj.Ambalaj.aspects;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Aspect
//@Component
//public class LoggingAspect {
//
//    @AfterThrowing(pointcut = "execution(* com.Ambalaj.Ambalaj..*(..))", throwing = "ex")
//    public void logExceptions(JoinPoint joinPoint, Exception ex) {
//        log.error("{} An exception was thrown because of {}", joinPoint.getSignature(), ex.getMessage());
//    }
//}

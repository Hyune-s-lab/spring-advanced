package com.example.aop.proxyvs.code

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aspect
class ProxyDIAspect {
    @Before("execution(* com.example.aop..*.*(..))")
    fun doTrace(joinPoint: JoinPoint) {
        log.info("[proxyDIAdvice] {}", joinPoint.signature)
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

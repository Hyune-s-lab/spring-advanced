package com.example.aop.internalcall.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aspect
class CallLogAspect {
    @Before("execution(* com.example.aop.internalcall..*.*(..))")
    fun doLog(joinPoint: JoinPoint) {
        log.info("aop={}", joinPoint.signature)
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

package com.example.aop.order.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aspect
class AspectV1 {
    @Around("execution(* com.example.aop.order..*(..))")
    @Throws(Throwable::class)
    fun doLog(joinPoint: ProceedingJoinPoint): Any? {
        log.info("[log] {}", joinPoint.signature) //join point 시그니처
        return joinPoint.proceed()
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

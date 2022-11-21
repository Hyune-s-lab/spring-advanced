package com.example.aop.order.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aspect
class AspectV2 {
    @Pointcut("execution(* com.example.aop.order..*(..))")
    private fun allOrder() {
    } //pointcut signature

    @Around("allOrder()")
    @Throws(Throwable::class)
    fun doLog(joinPoint: ProceedingJoinPoint): Any? {
        log.info("[log] {}", joinPoint.signature) //join point 시그니처
        return joinPoint.proceed()
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

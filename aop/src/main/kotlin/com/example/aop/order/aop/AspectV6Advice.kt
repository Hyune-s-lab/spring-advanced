package com.example.aop.order.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aspect
class AspectV6Advice {
    @Around("com.example.aop.order.aop.Pointcuts.orderAndService()")
    @Throws(Throwable::class)
    fun doTransaction(joinPoint: ProceedingJoinPoint): Any? {
        return try {
            //@Before
            log.info("[트랜잭션 시작] {}", joinPoint.signature)
            val result: Any? = joinPoint.proceed()
            //@AfterReturning
            log.info("[트랜잭션 커밋] {}", joinPoint.signature)
            result
        } catch (e: Exception) {
            //@AfterThrowing
            log.info("[트랜잭션 롤백] {}", joinPoint.signature)
            throw e
        } finally {
            //@After
            log.info("[리소스 릴리즈] {}", joinPoint.signature)
        }
    }

    @Before("com.example.aop.order.aop.Pointcuts.orderAndService()")
    fun doBefore(joinPoint: JoinPoint) {
        log.info("[before] {}", joinPoint.signature)
    }

    @AfterReturning(value = "com.example.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
    fun doReturn(joinPoint: JoinPoint, result: Any?) {
        log.info("[return] {} return={}", joinPoint.signature, result)
    }

    @AfterThrowing(value = "com.example.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    fun doThrowing(joinPoint: JoinPoint?, ex: Exception?) {
        log.info("[ex] {} message={}", ex)
    }

    @After(value = "com.example.aop.order.aop.Pointcuts.orderAndService()")
    fun doAfter(joinPoint: JoinPoint) {
        log.info("[after] {}", joinPoint.signature)
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

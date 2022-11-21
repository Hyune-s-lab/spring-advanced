package com.example.aop.order.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order

class AspectV5Order {
    @Aspect
    @Order(2)
    class LogAspect {
        @Around("com.example.aop.order.aop.Pointcuts.allOrder()")
        @Throws(Throwable::class)
        fun doLog(joinPoint: ProceedingJoinPoint): Any? {
            log.info("[log] {}", joinPoint.signature) //join point 시그니처
            return joinPoint.proceed()
        }
    }

    @Aspect
    @Order(1)
    class TxAspect {
        @Around("com.example.aop.order.aop.Pointcuts.orderAndService()")
        @Throws(Throwable::class)
        fun doTransaction(joinPoint: ProceedingJoinPoint): Any? {
            return try {
                log.info("[트랜잭션 시작] {}", joinPoint.signature)
                val result: Any? = joinPoint.proceed()
                log.info("[트랜잭션 커밋] {}", joinPoint.signature)
                result
            } catch (e: Exception) {
                log.info("[트랜잭션 롤백] {}", joinPoint.signature)
                throw e
            } finally {
                log.info("[리소스 릴리즈] {}", joinPoint.signature)
            }
        }
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

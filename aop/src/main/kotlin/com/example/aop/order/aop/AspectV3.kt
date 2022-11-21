package com.example.aop.order.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aspect
class AspectV3 {
    @Pointcut("execution(* com.example.aop.order..*(..))")
    private fun allOrder() {
    } //pointcut signature

    //클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    private fun allService() {
    }

    @Around("allOrder()")
    @Throws(Throwable::class)
    fun doLog(joinPoint: ProceedingJoinPoint): Any? {
        log.info("[log] {}", joinPoint.signature) //join point 시그니처
        return joinPoint.proceed()
    }

    //hello.aop.order 패키지와 하위 패키지 이면서 클래스 이름 패턴이 *Service
    @Around("allOrder() && allService()")
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

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

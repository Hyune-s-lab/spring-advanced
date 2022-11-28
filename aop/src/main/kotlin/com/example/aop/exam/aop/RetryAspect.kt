package com.example.aop.exam.aop

import com.example.aop.exam.annotation.Retry
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aspect
class RetryAspect {
    @Around("@annotation(retry)")
    @Throws(Throwable::class)
    fun doRetry(joinPoint: ProceedingJoinPoint, retry: Retry): Any {
        log.info("[retry] {} retry={}", joinPoint.signature, retry)
        val maxRetry: Int = retry.value
        lateinit var exceptionHolder: Exception
        for (retryCount in 1..maxRetry) {
            exceptionHolder = try {
                log.info("[retry] try count={}/{}", retryCount, maxRetry)
                return joinPoint.proceed()
            } catch (e: Exception) {
                e
            }
        }
        throw exceptionHolder
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

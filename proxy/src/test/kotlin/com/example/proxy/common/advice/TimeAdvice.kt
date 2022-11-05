package com.example.proxy.common.advice

import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TimeAdvice : MethodInterceptor {
    @Throws(Throwable::class)
    override fun invoke(invocation: MethodInvocation): Any? {
        log.info("TimeProxy 실행")
        val startTime = System.currentTimeMillis()
        val result = invocation.proceed()
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("TimeProxy 종료 resultTime={}", resultTime)
        return result
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

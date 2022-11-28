package com.example.aop.exam.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aspect
class TraceAspect {
    @Before("@annotation(com.example.aop.exam.annotation.Trace)")
    fun doTrace(joinPoint: JoinPoint) {
        val args: Array<Any> = joinPoint.args
        log.info("[trace] {} args={}", joinPoint.signature, args)
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

package com.example.proxy.config.v6_aop.aspect

import com.example.proxy.trace.TraceStatus
import com.example.proxy.trace.logtrace.LogTrace
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aspect
class LogTraceAspect(private val logTrace: LogTrace) {
    @Around("execution(* com.example.proxy.app..*(..))")
    @Throws(Throwable::class)
    fun execute(joinPoint: ProceedingJoinPoint): Any {
        lateinit var status: TraceStatus
        return try {
            val message: String = joinPoint.signature.toShortString()
            status = logTrace.begin(message)

            //로직 호출
            val result: Any = joinPoint.proceed()
            logTrace.end(status)
            result
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

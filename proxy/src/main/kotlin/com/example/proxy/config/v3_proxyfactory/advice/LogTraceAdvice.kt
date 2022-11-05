package com.example.proxy.config.v3_proxyfactory.advice

import com.example.proxy.trace.logtrace.LogTrace
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation

class LogTraceAdvice(private val logTrace: LogTrace) : MethodInterceptor {
    @Throws(Throwable::class)
    override fun invoke(invocation: MethodInvocation): Any? {
        val method = invocation.method
        val message = "${method.declaringClass.simpleName}.${method.name}()"
        val status = logTrace.begin(message)
        return try {
            //로직 호출
            val result = invocation.proceed()
            logTrace.end(status)
            result
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }
    }
}

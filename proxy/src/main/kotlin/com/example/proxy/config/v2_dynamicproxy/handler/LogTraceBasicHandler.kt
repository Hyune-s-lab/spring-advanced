package com.example.proxy.config.v2_dynamicproxy.handler

import com.example.proxy.trace.logtrace.LogTrace
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class LogTraceBasicHandler(private val target: Any, private val logTrace: LogTrace) : InvocationHandler {
    @Throws(Throwable::class)
    override fun invoke(proxy: Any, method: Method, args: Array<Any>): Any {
        val message = "${method.declaringClass.simpleName}.${method.name}()"
        val status = logTrace.begin(message)

        return try {
            //로직 호출
            val result = method.invoke(target, *args)
            logTrace.end(status)
            result
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }
    }
}

package com.example.proxy.config.v2_dynamicproxy.handler

import com.example.proxy.trace.logtrace.LogTrace
import org.springframework.util.PatternMatchUtils
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class LogTraceFilterHandler(private val target: Any, logTrace: LogTrace, patterns: Array<String>) : InvocationHandler {
    private val logTrace: LogTrace
    private val patterns: Array<String>

    init {
        this.logTrace = logTrace
        this.patterns = patterns
    }

    @Throws(Throwable::class)
    override fun invoke(proxy: Any, method: Method, args: Array<Any>): Any {
        //메서드 이름 필터
        val methodName = method.name
        if (!PatternMatchUtils.simpleMatch(patterns, methodName)) {
            return method.invoke(target, *args)
        }
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

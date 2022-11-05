package com.example.proxy.jdkdynamic.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class TimeInvocationHandler(private val target: Any) : InvocationHandler {

    @Throws(Throwable::class)
    override fun invoke(proxy: Any, method: Method, args: Array<Any>): Any {
        log.info("TimeProxy 실행")
        val startTime = System.currentTimeMillis()
        val result = method.invoke(target, *args)
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("TimeProxy 종료 resultTime={}", resultTime)
        return result
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

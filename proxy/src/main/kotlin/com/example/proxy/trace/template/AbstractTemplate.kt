package com.example.proxy.trace.template

import com.example.proxy.trace.TraceStatus
import com.example.proxy.trace.logtrace.LogTrace

abstract class AbstractTemplate<T>(private val trace: LogTrace) {
    fun execute(message: String?): T {
        lateinit var status: TraceStatus
        return try {
            status = trace.begin(message)
            //로직 호출
            val result = call()
            trace.end(status)
            result
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }

    protected abstract fun call(): T
}

package com.example.advanced.trace.callback

import com.example.advanced.trace.TraceStatus
import com.example.advanced.trace.logtrace.LogTrace

class TraceTemplate(private val trace: LogTrace) {
    fun <T> execute(message: String?, callback: TraceCallback<T>): T {
        lateinit var status: TraceStatus
        return try {
            status = trace.begin(message)
            //로직 호출
            val result = callback.call()
            trace.end(status)
            result
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }
}

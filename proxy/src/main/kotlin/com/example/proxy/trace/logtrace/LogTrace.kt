package com.example.proxy.trace.logtrace

import com.example.proxy.trace.TraceStatus

interface LogTrace {
    fun begin(message: String?): TraceStatus
    fun end(status: TraceStatus)
    fun exception(status: TraceStatus, e: Exception)
}

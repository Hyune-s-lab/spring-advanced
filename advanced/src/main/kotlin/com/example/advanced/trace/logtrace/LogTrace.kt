package com.example.advanced.trace.logtrace

import com.example.advanced.trace.TraceStatus

interface LogTrace {
    fun begin(message: String?): TraceStatus
    fun end(status: TraceStatus)
    fun exception(status: TraceStatus, e: Exception)
}

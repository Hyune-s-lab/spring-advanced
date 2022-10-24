package com.example.advanced.trace.logtrace

import com.example.advanced.trace.TraceStatus
import org.junit.jupiter.api.Test

class ThreadLocalLogTraceTest {
    var trace = ThreadLocalLogTrace()

    @Test
    fun begin_end_level2() {
        val status1: TraceStatus = trace.begin("hello1")
        val status2: TraceStatus = trace.begin("hello2")
        trace.end(status2)
        trace.end(status1)
    }

    @Test
    fun begin_exception_level2() {
        val status1: TraceStatus = trace.begin("hello1")
        val status2: TraceStatus = trace.begin("hello2")
        trace.exception(status2, IllegalStateException())
        trace.exception(status1, IllegalStateException())
    }
}

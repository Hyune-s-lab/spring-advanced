package com.example.advanced.app.v2

import com.example.advanced.trace.TraceId
import com.example.advanced.trace.hellotrace.HelloTraceV2
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV2(private val trace: HelloTraceV2) {
    fun save(traceId: TraceId, itemId: String) {
        val status = trace.beginSync(traceId, "OrderRepository.save()")

        try {
            //저장 로직
            check(itemId != "ex") { "예외 발생!" }
            sleep(1000)
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }

    private fun sleep(millis: Int) {
        try {
            Thread.sleep(millis.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}

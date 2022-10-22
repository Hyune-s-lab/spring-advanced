package com.example.advanced.app.v1

import com.example.advanced.trace.hellotrace.HelloTraceV1
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV1(private val trace: HelloTraceV1) {
    fun save(itemId: String) {
        val status = trace.begin("OrderRepository.save()")

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

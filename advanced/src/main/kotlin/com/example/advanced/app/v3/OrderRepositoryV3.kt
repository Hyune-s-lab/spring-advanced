package com.example.advanced.app.v3

import com.example.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV3(private val trace: LogTrace) {
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

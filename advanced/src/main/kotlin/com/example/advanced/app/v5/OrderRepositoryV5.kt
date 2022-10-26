package com.example.advanced.app.v5

import com.example.advanced.trace.callback.TraceCallback
import com.example.advanced.trace.callback.TraceTemplate
import com.example.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV5(trace: LogTrace) {
    var template: TraceTemplate = TraceTemplate(trace)

    fun save(itemId: String) {
        template.execute("OrderRepository.save()", object : TraceCallback<Unit> {
            override fun call() {
                check(itemId != "ex") { "예외 발생!" }
                sleep(1000)
            }
        })
    }

    private fun sleep(millis: Int) {
        try {
            Thread.sleep(millis.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}

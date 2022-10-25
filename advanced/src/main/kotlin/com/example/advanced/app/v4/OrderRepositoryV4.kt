package com.example.advanced.app.v4

import com.example.advanced.trace.logtrace.LogTrace
import com.example.advanced.trace.template.AbstractTemplate
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV4(private val trace: LogTrace) {
    fun save(itemId: String) {
        val template: AbstractTemplate<Unit> = object : AbstractTemplate<Unit>(trace) {
            override fun call() {
                check(itemId != "ex") { "예외 발생!" }
                sleep(1000)
            }
        }
        template.execute("OrderRepository.save()")
    }

    private fun sleep(millis: Int) {
        try {
            Thread.sleep(millis.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}

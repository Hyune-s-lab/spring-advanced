package com.example.advanced.app.v5

import com.example.advanced.trace.callback.TraceCallback
import com.example.advanced.trace.callback.TraceTemplate
import com.example.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Service

@Service
class OrderServiceV5(
    private val orderRepository: OrderRepositoryV5,
    trace: LogTrace
) {
    var template: TraceTemplate = TraceTemplate(trace)

    fun orderItem(itemId: String) {
        template.execute("OrderService.orderItem()", object : TraceCallback<Unit> {
            override fun call() {
                orderRepository.save(itemId)
            }
        })
    }
}

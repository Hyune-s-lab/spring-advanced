package com.example.advanced.app.v4

import com.example.advanced.trace.logtrace.LogTrace
import com.example.advanced.trace.template.AbstractTemplate
import org.springframework.stereotype.Service

@Service
class OrderServiceV4(
    private val orderRepository: OrderRepositoryV4,
    private val trace: LogTrace
) {
    fun orderItem(itemId: String) {
        val template: AbstractTemplate<Unit> = object : AbstractTemplate<Unit>(trace) {
            override fun call() {
                orderRepository.save(itemId)
            }
        }
        template.execute("OrderService.orderItem()")
    }
}

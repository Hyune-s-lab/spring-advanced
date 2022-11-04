package com.example.proxy.config.v1_proxy.concrete_proxy

import com.example.proxy.app.v2.OrderServiceV2
import com.example.proxy.trace.logtrace.LogTrace

class OrderServiceConcreteProxy(target: OrderServiceV2, logTrace: LogTrace) : OrderServiceV2(null) {
    private val target: OrderServiceV2
    private val logTrace: LogTrace

    init {
        this.target = target
        this.logTrace = logTrace
    }

    override fun orderItem(itemId: String) {
        val status = logTrace.begin("OrderService.orderItem()")
        try {
            //target 호출
            target.orderItem(itemId)
            logTrace.end(status)
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }
    }
}

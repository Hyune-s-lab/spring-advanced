package com.example.proxy.config.v1_proxy.interface_proxy

import com.example.proxy.app.v1.OrderServiceV1
import com.example.proxy.trace.logtrace.LogTrace

class OrderServiceInterfaceProxy(
    private val target: OrderServiceV1,
    private val logTrace: LogTrace
) : OrderServiceV1 {
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

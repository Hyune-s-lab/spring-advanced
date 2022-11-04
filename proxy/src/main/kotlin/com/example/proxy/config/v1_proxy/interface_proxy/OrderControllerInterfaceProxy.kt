package com.example.proxy.config.v1_proxy.interface_proxy

import com.example.proxy.app.v1.OrderControllerV1
import com.example.proxy.trace.logtrace.LogTrace

class OrderControllerInterfaceProxy(
    private val target: OrderControllerV1,
    private val logTrace: LogTrace
) : OrderControllerV1 {
    override fun request(itemId: String): String {
        val status = logTrace.begin("OrderController.request()")
        return try {
            //target 호출
            val result: String = target.request(itemId)
            logTrace.end(status)
            result
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }
    }

    override fun noLog(): String {
        return target.noLog()
    }
}

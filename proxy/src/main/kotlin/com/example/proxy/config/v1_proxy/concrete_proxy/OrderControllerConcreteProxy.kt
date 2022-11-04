package com.example.proxy.config.v1_proxy.concrete_proxy

import com.example.proxy.app.v2.OrderControllerV2
import com.example.proxy.trace.logtrace.LogTrace

class OrderControllerConcreteProxy(target: OrderControllerV2, logTrace: LogTrace) : OrderControllerV2(null) {
    private val target: OrderControllerV2
    private val logTrace: LogTrace

    init {
        this.target = target
        this.logTrace = logTrace
    }

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

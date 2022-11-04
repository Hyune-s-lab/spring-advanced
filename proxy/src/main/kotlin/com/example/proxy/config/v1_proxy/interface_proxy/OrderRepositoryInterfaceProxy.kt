package com.example.proxy.config.v1_proxy.interface_proxy

import com.example.proxy.app.v1.OrderRepositoryV1
import com.example.proxy.trace.logtrace.LogTrace

class OrderRepositoryInterfaceProxy(
    private val target: OrderRepositoryV1,
    private val logTrace: LogTrace
) : OrderRepositoryV1 {

    override fun save(itemId: String) {
        val status = logTrace.begin("OrderRepository.request()")
        try {
            //target 호출
            target.save(itemId)
            logTrace.end(status)
        } catch (e: Exception) {
            logTrace.exception(status, e)
            throw e
        }
    }
}

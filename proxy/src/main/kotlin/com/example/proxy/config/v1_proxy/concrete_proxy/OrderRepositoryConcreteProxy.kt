package com.example.proxy.config.v1_proxy.concrete_proxy

import com.example.proxy.app.v2.OrderRepositoryV2
import com.example.proxy.trace.logtrace.LogTrace

class OrderRepositoryConcreteProxy(target: OrderRepositoryV2, logTrace: LogTrace) : OrderRepositoryV2() {
    private val target: OrderRepositoryV2
    private val logTrace: LogTrace

    init {
        this.target = target
        this.logTrace = logTrace
    }

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

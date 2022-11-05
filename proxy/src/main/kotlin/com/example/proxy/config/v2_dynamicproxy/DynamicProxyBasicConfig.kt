package com.example.proxy.config.v2_dynamicproxy

import com.example.proxy.app.v1.OrderControllerV1
import com.example.proxy.app.v1.OrderControllerV1Impl
import com.example.proxy.app.v1.OrderRepositoryV1
import com.example.proxy.app.v1.OrderRepositoryV1Impl
import com.example.proxy.app.v1.OrderServiceV1
import com.example.proxy.app.v1.OrderServiceV1Impl
import com.example.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler
import com.example.proxy.trace.logtrace.LogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.lang.reflect.Proxy

@Configuration
class DynamicProxyBasicConfig {
    @Bean
    fun orderControllerV1(logTrace: LogTrace): OrderControllerV1 {
        val orderControllerV1: OrderControllerV1 = OrderControllerV1Impl(orderServiceV1(logTrace))
        return Proxy.newProxyInstance(
            OrderControllerV1::class.java.classLoader, arrayOf<Class<*>>(OrderControllerV1::class.java),
            LogTraceBasicHandler(orderControllerV1, logTrace)
        ) as OrderControllerV1
    }

    @Bean
    fun orderServiceV1(logTrace: LogTrace): OrderServiceV1 {
        val orderServiceV1: OrderServiceV1 = OrderServiceV1Impl(orderRepositoryV1(logTrace))
        return Proxy.newProxyInstance(
            OrderServiceV1::class.java.classLoader, arrayOf<Class<*>>(OrderServiceV1::class.java),
            LogTraceBasicHandler(orderServiceV1, logTrace)
        ) as OrderServiceV1
    }

    @Bean
    fun orderRepositoryV1(logTrace: LogTrace): OrderRepositoryV1 {
        val orderRepository: OrderRepositoryV1 = OrderRepositoryV1Impl()
        return Proxy.newProxyInstance(
            OrderRepositoryV1::class.java.classLoader, arrayOf<Class<*>>(OrderRepositoryV1::class.java),
            LogTraceBasicHandler(orderRepository, logTrace)
        ) as OrderRepositoryV1
    }
}

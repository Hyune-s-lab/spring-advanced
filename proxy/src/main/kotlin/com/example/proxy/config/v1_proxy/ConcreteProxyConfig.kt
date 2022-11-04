package com.example.proxy.config.v1_proxy

import com.example.proxy.app.v2.OrderControllerV2
import com.example.proxy.app.v2.OrderRepositoryV2
import com.example.proxy.app.v2.OrderServiceV2
import com.example.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy
import com.example.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy
import com.example.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy
import com.example.proxy.trace.logtrace.LogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ConcreteProxyConfig {
    @Bean
    fun orderControllerV2(logTrace: LogTrace): OrderControllerV2 {
        val controllerImpl = OrderControllerV2(orderServiceV2(logTrace))
        return OrderControllerConcreteProxy(controllerImpl, logTrace)
    }

    @Bean
    fun orderServiceV2(logTrace: LogTrace): OrderServiceV2 {
        val serviceImpl = OrderServiceV2(orderRepositoryV2(logTrace))
        return OrderServiceConcreteProxy(serviceImpl, logTrace)
    }

    @Bean
    fun orderRepositoryV2(logTrace: LogTrace): OrderRepositoryV2 {
        val repositoryImpl = OrderRepositoryV2()
        return OrderRepositoryConcreteProxy(repositoryImpl, logTrace)
    }
}

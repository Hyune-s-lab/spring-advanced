package com.example.proxy.config.v1_proxy

import com.example.proxy.app.v1.OrderControllerV1
import com.example.proxy.app.v1.OrderControllerV1Impl
import com.example.proxy.app.v1.OrderRepositoryV1
import com.example.proxy.app.v1.OrderRepositoryV1Impl
import com.example.proxy.app.v1.OrderServiceV1
import com.example.proxy.app.v1.OrderServiceV1Impl
import com.example.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy
import com.example.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy
import com.example.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy
import com.example.proxy.trace.logtrace.LogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InterfaceProxyConfig {
    @Bean
    fun orderController(logTrace: LogTrace): OrderControllerV1 {
        val controllerImpl = OrderControllerV1Impl(orderService(logTrace))
        return OrderControllerInterfaceProxy(controllerImpl, logTrace)
    }

    @Bean
    fun orderService(logTrace: LogTrace): OrderServiceV1 {
        val serviceImpl = OrderServiceV1Impl(orderRepository(logTrace))
        return OrderServiceInterfaceProxy(serviceImpl, logTrace)
    }

    @Bean
    fun orderRepository(logTrace: LogTrace): OrderRepositoryV1 {
        val repositoryImpl = OrderRepositoryV1Impl()
        return OrderRepositoryInterfaceProxy(repositoryImpl, logTrace)
    }
}

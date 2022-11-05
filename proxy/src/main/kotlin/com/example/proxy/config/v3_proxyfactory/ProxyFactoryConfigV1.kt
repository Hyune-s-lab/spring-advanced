package com.example.proxy.config.v3_proxyfactory

import com.example.proxy.app.v1.OrderControllerV1
import com.example.proxy.app.v1.OrderControllerV1Impl
import com.example.proxy.app.v1.OrderRepositoryV1
import com.example.proxy.app.v1.OrderRepositoryV1Impl
import com.example.proxy.app.v1.OrderServiceV1
import com.example.proxy.app.v1.OrderServiceV1Impl
import com.example.proxy.config.v3_proxyfactory.advice.LogTraceAdvice
import com.example.proxy.trace.logtrace.LogTrace
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.Advisor
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProxyFactoryConfigV1 {
    @Bean
    fun orderControllerV1(logTrace: LogTrace): OrderControllerV1 {
        val orderController: OrderControllerV1 = OrderControllerV1Impl(orderServiceV1(logTrace))
        val factory = ProxyFactory(orderController)
        factory.addAdvisor(getAdvisor(logTrace))
        val proxy: OrderControllerV1 = factory.proxy as OrderControllerV1
        log.info("ProxyFactory proxy={}, target={}", proxy::class.java, orderController::class.java)
        return proxy
    }

    @Bean
    fun orderServiceV1(logTrace: LogTrace): OrderServiceV1 {
        val orderService: OrderServiceV1 = OrderServiceV1Impl(orderRepositoryV1(logTrace))
        val factory = ProxyFactory(orderService)
        factory.addAdvisor(getAdvisor(logTrace))
        val proxy: OrderServiceV1 = factory.proxy as OrderServiceV1
        log.info("ProxyFactory proxy={}, target={}", proxy::class.java, orderService::class.java)
        return proxy
    }

    @Bean
    fun orderRepositoryV1(logTrace: LogTrace): OrderRepositoryV1 {
        val orderRepository = OrderRepositoryV1Impl()
        val factory = ProxyFactory(orderRepository)
        factory.addAdvisor(getAdvisor(logTrace))
        val proxy: OrderRepositoryV1 = factory.proxy as OrderRepositoryV1
        log.info("ProxyFactory proxy={}, target={}", proxy::class.java, orderRepository::class.java)
        return proxy
    }

    private fun getAdvisor(logTrace: LogTrace): Advisor {
        //pointcut
        val pointcut = NameMatchMethodPointcut()
        pointcut.setMappedNames("request*", "order*", "save*")
        //advice
        val advice = LogTraceAdvice(logTrace)
        return DefaultPointcutAdvisor(pointcut, advice)
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

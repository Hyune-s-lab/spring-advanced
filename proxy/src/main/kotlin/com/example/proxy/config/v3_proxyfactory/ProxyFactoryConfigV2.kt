package com.example.proxy.config.v3_proxyfactory

import com.example.proxy.app.v2.OrderControllerV2
import com.example.proxy.app.v2.OrderRepositoryV2
import com.example.proxy.app.v2.OrderServiceV2
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
class ProxyFactoryConfigV2 {
    @Bean
    fun orderControllerV2(logTrace: LogTrace): OrderControllerV2 {
        val orderController = OrderControllerV2(orderServiceV2(logTrace))
        val factory = ProxyFactory(orderController)
        factory.addAdvisor(getAdvisor(logTrace))
        val proxy: OrderControllerV2 = factory.proxy as OrderControllerV2
        log.info("ProxyFactory proxy={}, target={}", proxy::class.java, orderController::class.java)
        return proxy
    }

    @Bean
    fun orderServiceV2(logTrace: LogTrace): OrderServiceV2 {
        val orderService = OrderServiceV2(orderRepositoryV2(logTrace))
        val factory = ProxyFactory(orderService)
        factory.addAdvisor(getAdvisor(logTrace))
        val proxy: OrderServiceV2 = factory.proxy as OrderServiceV2
        log.info("ProxyFactory proxy={}, target={}", proxy::class.java, orderService::class.java)
        return proxy
    }

    @Bean
    fun orderRepositoryV2(logTrace: LogTrace): OrderRepositoryV2 {
        val orderRepository = OrderRepositoryV2()
        val factory = ProxyFactory(orderRepository)
        factory.addAdvisor(getAdvisor(logTrace))
        val proxy: OrderRepositoryV2 = factory.proxy as OrderRepositoryV2
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

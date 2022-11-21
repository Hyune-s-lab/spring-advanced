package com.example.aop

import com.example.aop.order.OrderRepository
import com.example.aop.order.OrderService
import com.example.aop.order.aop.AspectV3
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.support.AopUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

//@Import(AspectV1::class)
//@Import(AspectV2::class)
@Import(AspectV3::class)
@SpringBootTest
class AopTest {
    @Autowired
    lateinit var orderService: OrderService

    @Autowired
    lateinit var orderRepository: OrderRepository

    @Test
    fun aopInfo() {
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService))
        log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository))
    }

    @Test
    fun success() {
        orderService.orderItem("itemA")
    }

    @Test
    fun exception() {
        assertThatThrownBy { orderService.orderItem("ex") }
            .isInstanceOf(IllegalStateException::class.java)
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

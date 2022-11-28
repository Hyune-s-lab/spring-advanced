package com.example.aop.pointcut

import com.example.aop.order.OrderService
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(BeanTest.BeanAspect::class)
@SpringBootTest
class BeanTest {
    @Autowired
    lateinit var orderService: OrderService

    @Test
    fun success() {
        orderService.orderItem("itemA")
    }

    @Aspect
    internal class BeanAspect {
        @Around("bean(orderService) || bean(*Repository)")
        @Throws(Throwable::class)
        fun doLog(joinPoint: ProceedingJoinPoint): Any? {
            log.info("[bean] {}", joinPoint.signature)
            return joinPoint.proceed()
        }
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

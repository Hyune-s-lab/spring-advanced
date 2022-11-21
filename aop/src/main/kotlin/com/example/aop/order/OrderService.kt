package com.example.aop.order

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class OrderService(private val orderRepository: OrderRepository) {
    fun orderItem(itemId: String) {
        log.info("[orderService] 실행")
        orderRepository.save(itemId)
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

package com.example.advanced.app.v0

import org.springframework.stereotype.Service

@Service
class OrderServiceV0(private val orderRepository: OrderRepositoryV0) {
    fun orderItem(itemId: String) {
        orderRepository.save(itemId)
    }
}

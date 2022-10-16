package com.example.advanced.app.v0

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV0(private val orderService: OrderServiceV0) {
    @GetMapping("/v0/request")
    fun request(itemId: String): String {
        orderService.orderItem(itemId)
        return "ok"
    }
}

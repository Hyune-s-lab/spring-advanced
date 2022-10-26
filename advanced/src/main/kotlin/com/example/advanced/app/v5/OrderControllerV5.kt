package com.example.advanced.app.v5

import com.example.advanced.trace.callback.TraceCallback
import com.example.advanced.trace.callback.TraceTemplate
import com.example.advanced.trace.logtrace.LogTrace
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV5(
    private val orderService: OrderServiceV5,
    trace: LogTrace
) {
    var template: TraceTemplate = TraceTemplate(trace)

    @GetMapping("/v5/request")
    fun request(itemId: String): String {
        return template.execute("OrderController.request()", object : TraceCallback<String> {
            override fun call(): String {
                orderService.orderItem(itemId)
                return "ok"
            }
        })
    }
}

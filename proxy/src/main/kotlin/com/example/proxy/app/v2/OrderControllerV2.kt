package com.example.proxy.app.v2

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@RequestMapping //스프링은 @Controller 또는 @RequestMapping 이 있어야 스프링 컨트롤러로 인식
@ResponseBody
open class OrderControllerV2(private val orderService: OrderServiceV2?) {
    @GetMapping("/v2/request")
    open fun request(itemId: String): String {
        orderService!!.orderItem(itemId)
        return "ok"
    }

    @GetMapping("/v2/no-log")
    open fun noLog(): String {
        return "ok"
    }
}

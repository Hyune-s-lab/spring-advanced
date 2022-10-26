package com.example.proxy.app.v1

import org.springframework.web.bind.annotation.*

@RequestMapping //스프링은 @Controller 또는 @RequestMapping 이 있어야 스프링 컨트롤러로 인식
@ResponseBody
interface OrderControllerV1 {
    @GetMapping("/v1/request")
    fun request(@RequestParam("itemId") itemId: String): String

    @GetMapping("/v1/no-log")
    fun noLog(): String
}

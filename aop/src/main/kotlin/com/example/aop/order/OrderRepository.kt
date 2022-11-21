package com.example.aop.order

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class OrderRepository {
    fun save(itemId: String): String {
        log.info("[orderRepository] 실행")
        //저장 로직
        check(itemId != "ex") { "예외 발생!" }
        return "ok"
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

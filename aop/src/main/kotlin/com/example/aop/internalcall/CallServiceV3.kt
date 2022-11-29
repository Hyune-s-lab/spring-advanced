package com.example.aop.internalcall

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * 구조를 변경(분리)
 */
@Component
class CallServiceV3(private val internalService: InternalService) {
    fun external() {
        log.info("call external")
        internalService.internal() //외부 메서드 호출
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

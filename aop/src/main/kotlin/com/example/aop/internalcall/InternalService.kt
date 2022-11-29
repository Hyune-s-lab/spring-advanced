package com.example.aop.internalcall

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class InternalService {
    fun internal() {
        log.info("call internal")
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

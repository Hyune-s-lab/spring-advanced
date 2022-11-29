package com.example.aop.internalcall

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class CallServiceV0 {
    fun external() {
        log.info("call external")
        internal() //내부 메서드 호출(this.internal())
    }

    fun internal() {
        log.info("call internal")
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

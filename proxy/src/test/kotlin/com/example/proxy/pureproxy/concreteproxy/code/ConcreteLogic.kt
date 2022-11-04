package com.example.proxy.pureproxy.concreteproxy.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

open class ConcreteLogic {
    open fun operation(): String {
        log.info("ConcreteLogic 실행")
        return "data"
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

package com.example.proxy.pureproxy.decorator.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class RealComponent : Component {
    override fun operation(): String {
        log.info("RealComponent 실행")
        return "data"
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

package com.example.proxy.jdkdynamic.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class BImpl : BInterface {
    override fun call(): String {
        log.info("B 호출")
        return "b"
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

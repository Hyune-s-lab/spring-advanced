package com.example.proxy.jdkdynamic.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class AImpl : AInterface {
    override fun call(): String {
        log.info("A 호출")
        return "a"
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

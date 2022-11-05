package com.example.proxy.common.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ConcreteService {
    fun call() {
        log.info("ConcreteService 호출")
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

package com.example.proxy.common.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ServiceImpl : ServiceInterface {
    override fun save() {
        log.info("save 호출")
    }

    override fun find() {
        log.info("find 호출")
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

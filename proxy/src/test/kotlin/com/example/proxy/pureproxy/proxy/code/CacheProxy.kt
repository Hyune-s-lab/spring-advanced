package com.example.proxy.pureproxy.proxy.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CacheProxy(private val target: Subject) : Subject {
    private var cacheValue: String? = null

    override fun operation(): String {
        log.info("프록시 호출")
        if (cacheValue == null) {
            cacheValue = target.operation()
        }
        return cacheValue!!
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

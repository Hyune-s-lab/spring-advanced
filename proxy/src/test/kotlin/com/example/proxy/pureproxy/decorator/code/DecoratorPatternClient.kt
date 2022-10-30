package com.example.proxy.pureproxy.decorator.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class DecoratorPatternClient(private val component: Component) {
    fun execute() {
        val result = component.operation()
        log.info("result={}", result)
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

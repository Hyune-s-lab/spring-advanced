package com.example.proxy.pureproxy.decorator.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TimeDecorator(private val component: Component) : Component {
    override fun operation(): String {
        log.info("TimeDecorator 실행")
        val startTime = System.currentTimeMillis()
        val result: String = component.operation()
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("TimeDecorator 종료 resultTime={}ms", resultTime)
        return result
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

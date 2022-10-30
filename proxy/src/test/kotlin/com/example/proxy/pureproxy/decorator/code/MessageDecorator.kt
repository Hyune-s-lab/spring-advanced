package com.example.proxy.pureproxy.decorator.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class MessageDecorator(private val component: Component) : Component {
    override fun operation(): String {
        log.info("MessageDecorator 실행")

        //data -> *****data*****
        val result = component.operation()
        val decoResult = "*****$result*****"
        log.info("MessageDecorator 꾸미기 적용 전={}, 적용 후={}", result, decoResult)
        return decoResult
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

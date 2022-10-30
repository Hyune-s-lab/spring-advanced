package com.example.proxy.pureproxy.decorator

import com.example.proxy.pureproxy.decorator.code.Component
import com.example.proxy.pureproxy.decorator.code.DecoratorPatternClient
import com.example.proxy.pureproxy.decorator.code.RealComponent
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class DecoratorPatternTest {
    @Test
    fun noDecorator() {
        val realComponent: Component = RealComponent()
        val client = DecoratorPatternClient(realComponent)
        client.execute()
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

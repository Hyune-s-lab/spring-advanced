package com.example.proxy.pureproxy.decorator

import com.example.proxy.pureproxy.decorator.code.Component
import com.example.proxy.pureproxy.decorator.code.DecoratorPatternClient
import com.example.proxy.pureproxy.decorator.code.MessageDecorator
import com.example.proxy.pureproxy.decorator.code.RealComponent
import com.example.proxy.pureproxy.decorator.code.TimeDecorator
import org.junit.jupiter.api.Test

class DecoratorPatternTest {
    @Test
    fun noDecorator() {
        val realComponent: Component = RealComponent()
        val client = DecoratorPatternClient(realComponent)
        client.execute()
    }

    @Test
    fun decorator1() {
        val realComponent: Component = RealComponent()
        val messageDecorator: Component = MessageDecorator(realComponent)
        val client = DecoratorPatternClient(messageDecorator)
        client.execute()
    }

    @Test
    fun decorator2() {
        val realComponent: Component = RealComponent()
        val messageDecorator: Component = MessageDecorator(realComponent)
        val timeDecorator: Component = TimeDecorator(messageDecorator)
        val client = DecoratorPatternClient(timeDecorator)
        client.execute()
    }
}

package com.example.proxy.jdkdynamic

import com.example.proxy.jdkdynamic.code.AImpl
import com.example.proxy.jdkdynamic.code.AInterface
import com.example.proxy.jdkdynamic.code.BImpl
import com.example.proxy.jdkdynamic.code.BInterface
import com.example.proxy.jdkdynamic.code.TimeInvocationHandler
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.reflect.Proxy

class JdkDynamicProxyTest {
    @Test
    fun dynamicA() {
        val target: AInterface = AImpl()
        val handler = TimeInvocationHandler(target)
        val proxy: AInterface =
            Proxy.newProxyInstance(AInterface::class.java.classLoader, arrayOf<Class<*>>(AInterface::class.java), handler) as AInterface
        proxy.call()
        log.info("targetClass={}", target::class.java)
        log.info("proxyClass={}", proxy::class.java)
    }

    @Test
    fun dynamicB() {
        val target: BInterface = BImpl()
        val handler = TimeInvocationHandler(target)
        val proxy: BInterface =
            Proxy.newProxyInstance(BInterface::class.java.classLoader, arrayOf<Class<*>>(BInterface::class.java), handler) as BInterface
        proxy.call()
        log.info("targetClass={}", target::class.java)
        log.info("proxyClass={}", proxy::class.java)
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

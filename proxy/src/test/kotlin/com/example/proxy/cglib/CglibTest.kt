package com.example.proxy.cglib

import com.example.proxy.cglib.code.TimeMethodInterceptor
import com.example.proxy.common.service.ConcreteService
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cglib.proxy.Enhancer

class CglibTest {
    @Test
    fun cglib() {
        val target = ConcreteService()
        val enhancer = Enhancer()
        enhancer.setSuperclass(ConcreteService::class.java)
        enhancer.setCallback(TimeMethodInterceptor(target))
        val proxy: ConcreteService = enhancer.create() as ConcreteService
        log.info("targetClass={}", target::class.java)
        log.info("proxyClass={}", proxy::class.java)
        proxy.call()
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

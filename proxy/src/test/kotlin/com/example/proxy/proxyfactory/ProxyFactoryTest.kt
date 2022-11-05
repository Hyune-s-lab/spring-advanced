package com.example.proxy.proxyfactory

import com.example.proxy.common.advice.TimeAdvice
import com.example.proxy.common.service.ConcreteService
import com.example.proxy.common.service.ServiceImpl
import com.example.proxy.common.service.ServiceInterface
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.AopUtils.*

class ProxyFactoryTest {
    @Test
    @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
    fun interfaceProxy() {
        val target: ServiceInterface = ServiceImpl()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.addAdvice(TimeAdvice())
        val proxy: ServiceInterface = proxyFactory.proxy as ServiceInterface
        log.info("targetClass={}", target::class.java)
        log.info("proxyClass={}", proxy::class.java)
        proxy.save()
        assertThat(isAopProxy(proxy)).isTrue
        assertThat(isJdkDynamicProxy(proxy)).isTrue
        assertThat(isCglibProxy(proxy)).isFalse
    }

    @Test
    @DisplayName("구체 클래스만 있으면 CGLIB 사용")
    fun concreteProxy() {
        val target = ConcreteService()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.addAdvice(TimeAdvice())
        val proxy: ConcreteService = proxyFactory.proxy as ConcreteService
        log.info("targetClass={}", target::class.java)
        log.info("proxyClass={}", proxy::class.java)
        proxy.call()
        assertThat(isAopProxy(proxy)).isTrue
        assertThat(isJdkDynamicProxy(proxy)).isFalse
        assertThat(isCglibProxy(proxy)).isTrue
    }

    @Test
    @DisplayName("ProxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLIB를 사용하고, 클래스 기반 프록시 사용")
    fun proxyTargetClass() {
        val target: ServiceInterface = ServiceImpl()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.isProxyTargetClass = true
        proxyFactory.addAdvice(TimeAdvice())
        val proxy: ServiceInterface = proxyFactory.proxy as ServiceInterface
        log.info("targetClass={}", target::class.java)
        log.info("proxyClass={}", proxy::class.java)
        proxy.save()
        assertThat(isAopProxy(proxy)).isTrue
        assertThat(isJdkDynamicProxy(proxy)).isFalse
        assertThat(isCglibProxy(proxy)).isTrue
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

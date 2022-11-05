package com.example.proxy.advisor

import com.example.proxy.common.service.ServiceImpl
import com.example.proxy.common.service.ServiceInterface
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.Pointcut
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor

class MultiAdvisorTest {
    @Test
    @DisplayName("여러 프록시")
    fun multiAdvisorTest1() {
        //client -> proxy2(advisor2) -> proxy1(advisor1) -> target

        //프록시1 생성
        val target: ServiceInterface = ServiceImpl()
        val proxyFactory1 = ProxyFactory(target)
        val advisor1 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice1())
        proxyFactory1.addAdvisor(advisor1)
        val proxy1: ServiceInterface = proxyFactory1.proxy as ServiceInterface

        //프록시2 생성, target -> proxy1 입력
        val proxyFactory2 = ProxyFactory(proxy1)
        val advisor2 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice2())
        proxyFactory2.addAdvisor(advisor2)
        val proxy2: ServiceInterface = proxyFactory2.proxy as ServiceInterface

        //실행
        proxy2.save()
    }

    @Test
    @DisplayName("하나의 프록시, 여러 어드바이저")
    fun multiAdvisorTest2() {
        //client -> proxy -> advisor2 -> advisor1 -> target
        val advisor1 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice1())
        val advisor2 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice2())

        //프록시1 생성
        val target: ServiceInterface = ServiceImpl()
        val proxyFactory1 = ProxyFactory(target)
        proxyFactory1.addAdvisor(advisor2)
        proxyFactory1.addAdvisor(advisor1)
        val proxy: ServiceInterface = proxyFactory1.proxy as ServiceInterface

        //실행
        proxy.save()
    }

    internal class Advice1 : MethodInterceptor {
        @Throws(Throwable::class)
        override fun invoke(invocation: MethodInvocation): Any? {
            log.info("advice1 호출")
            return invocation.proceed()
        }
    }

    internal class Advice2 : MethodInterceptor {
        @Throws(Throwable::class)
        override fun invoke(invocation: MethodInvocation): Any? {
            log.info("advice2 호출")
            return invocation.proceed()
        }
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

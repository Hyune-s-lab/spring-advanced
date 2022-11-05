package com.example.proxy.advisor

import com.example.proxy.common.advice.TimeAdvice
import com.example.proxy.common.service.ServiceImpl
import com.example.proxy.common.service.ServiceInterface
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.ClassFilter
import org.springframework.aop.MethodMatcher
import org.springframework.aop.Pointcut
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import java.lang.reflect.Method

class AdvisorTest {
    @Test
    fun advisorTest1() {
        val target: ServiceInterface = ServiceImpl()
        val proxyFactory = ProxyFactory(target)
        val advisor = DefaultPointcutAdvisor(Pointcut.TRUE, TimeAdvice())
        proxyFactory.addAdvisor(advisor)
        val proxy: ServiceInterface = proxyFactory.proxy as ServiceInterface
        proxy.save()
        proxy.find()
    }

    @Test
    @DisplayName("직접 만든 포인트컷")
    fun advisorTest2() {
        val target: ServiceInterface = ServiceImpl()
        val proxyFactory = ProxyFactory(target)
        val advisor = DefaultPointcutAdvisor(MyPointcut(), TimeAdvice())
        proxyFactory.addAdvisor(advisor)
        val proxy: ServiceInterface = proxyFactory.proxy as ServiceInterface
        proxy.save()
        proxy.find()
    }

    @Test
    @DisplayName("스프링이 제공하는 포인트컷")
    fun advisorTest3() {
        val target: ServiceInterface = ServiceImpl()
        val proxyFactory = ProxyFactory(target)
        val pointcut = NameMatchMethodPointcut()
        pointcut.setMappedNames("save")
        val advisor = DefaultPointcutAdvisor(pointcut, TimeAdvice())
        proxyFactory.addAdvisor(advisor)
        val proxy: ServiceInterface = proxyFactory.proxy as ServiceInterface
        proxy.save()
        proxy.find()
    }

    internal class MyPointcut : Pointcut {
        override fun getClassFilter(): ClassFilter {
            return ClassFilter.TRUE
        }

        override fun getMethodMatcher(): MethodMatcher {
            return MyMethodMatcher()
        }
    }

    internal class MyMethodMatcher : MethodMatcher {
        private val matchName = "save"

        override fun matches(method: Method, targetClass: Class<*>): Boolean {
            val result = method.name == matchName
            log.info("포인트컷 호출 method={} targetClass={}", method.name, targetClass)
            log.info("포인트컷 결과 result={}", result)
            return result
        }

        override fun matches(method: Method, targetClass: Class<*>, vararg args: Any?): Boolean {
            return false
        }

        override fun isRuntime(): Boolean {
            return false
        }
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

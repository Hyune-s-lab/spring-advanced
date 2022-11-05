package com.example.proxy.jdkdynamic

import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.reflect.Method

class ReflectionTest {
    @Test
    fun reflection0() {
        val target = Hello()

        //공통 로직1 시작
        log.info("start")
        val result1 = target.callA() //호출하는 메서드가 다름
        log.info("result={}", result1)
        //공통 로직1 종료

        //공통 로직2 시작
        log.info("start")
        val result2 = target.callB() //호출하는 메서드가 다름
        log.info("result={}", result2)
        //공통 로직2 종료
    }

    @Test
    @Throws(Exception::class)
    fun reflection1() {
        //클래스 정보
        val classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest\$Hello")
        val target = Hello()
        //callA 메서드 정보
        val methodCallA = classHello.getMethod("callA")
        val result1 = methodCallA.invoke(target)
        log.info("result1={}", result1)

        //callB 메서드 정보
        val methodCallB = classHello.getMethod("callB")
        val result2 = methodCallB.invoke(target)
        log.info("result2={}", result2)
    }

    @Test
    @Throws(Exception::class)
    fun reflection2() {
        //클래스 정보
        val classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest\$Hello")
        val target = Hello()
        val methodCallA = classHello.getMethod("callA")
        dynamicCall(methodCallA, target)
        val methodCallB = classHello.getMethod("callB")
        dynamicCall(methodCallB, target)
    }

    @Throws(Exception::class)
    private fun dynamicCall(method: Method, target: Any) {
        log.info("start")
        val result = method.invoke(target)
        log.info("result={}", result)
    }

    internal class Hello {
        fun callA(): String {
            log.info("callA")
            return "A"
        }

        fun callB(): String {
            log.info("callB")
            return "B"
        }
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

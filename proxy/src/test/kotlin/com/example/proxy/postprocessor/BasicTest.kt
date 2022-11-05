package com.example.proxy.postprocessor

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class BasicTest {
    @Test
    fun basicConfig() {
        val applicationContext: ApplicationContext = AnnotationConfigApplicationContext(BasicConfig::class.java)

        //A는 빈으로 등록된다.
        val a = applicationContext.getBean("beanA", A::class.java)
        a.helloA()

        //B는 빈으로 등록되지 않는다.
        assertThrows(NoSuchBeanDefinitionException::class.java) { applicationContext.getBean(B::class.java) }
    }

    @Configuration
    internal class BasicConfig {
        @Bean(name = ["beanA"])
        fun a(): A {
            return A()
        }
    }

    internal class A {
        fun helloA() {
            log.info("hello A")
        }
    }

    internal class B {
        fun helloB() {
            log.info("hello B")
        }
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

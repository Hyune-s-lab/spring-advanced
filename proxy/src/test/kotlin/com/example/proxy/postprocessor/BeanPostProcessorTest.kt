package com.example.proxy.postprocessor

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.BeansException
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class BeanPostProcessorTest {
    @Test
    fun basicConfig() {
        val applicationContext: ApplicationContext =
            AnnotationConfigApplicationContext(BeanPostProcessorConfig::class.java)

        //beanA 이름으로 B 객체가 빈으로 등록된다.
        val b = applicationContext.getBean("beanA", B::class.java)
        b.helloB()

        //A는 빈으로 등록되지 않는다.
        assertThrows(NoSuchBeanDefinitionException::class.java) { applicationContext.getBean(A::class.java) }
    }

    @Configuration
    internal class BeanPostProcessorConfig {
        @Bean(name = ["beanA"])
        fun a(): A {
            return A()
        }

        @Bean
        fun helloPostProcessor(): AToBPostProcessor {
            return AToBPostProcessor()
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

    internal class AToBPostProcessor : BeanPostProcessor {
        @Throws(BeansException::class)
        override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
            log.info("beanName={} bean={}", beanName, bean)
            return if (bean is A) {
                B()
            } else bean
        }
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

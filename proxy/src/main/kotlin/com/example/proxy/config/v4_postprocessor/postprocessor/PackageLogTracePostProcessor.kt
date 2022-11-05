package com.example.proxy.config.v4_postprocessor.postprocessor

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.Advisor
import org.springframework.aop.framework.ProxyFactory
import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.BeanPostProcessor

class PackageLogTracePostProcessor(private val basePackage: String, private val advisor: Advisor) : BeanPostProcessor {
    @Throws(BeansException::class)
    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
        log.info("param beanName={} bean={}", beanName, bean.javaClass)

        //프록시 적용 대상 여부 체크
        //프록시 적용 대상이 아니면 원본을 그대로 진행
        val packageName = bean.javaClass.packageName
        if (!packageName.startsWith(basePackage)) {
            return bean
        }

        //프록시 대상이면 프록시를 만들어서 반환
        val proxyFactory = ProxyFactory(bean)
        proxyFactory.addAdvisor(advisor)
        val proxy: Any = proxyFactory.proxy
        log.info("create proxy: target={} proxy={}", bean.javaClass, proxy.javaClass)
        return proxy
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

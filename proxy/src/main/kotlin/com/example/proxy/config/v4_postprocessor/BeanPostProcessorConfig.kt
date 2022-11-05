package com.example.proxy.config.v4_postprocessor

import com.example.proxy.config.AppV1Config
import com.example.proxy.config.AppV2Config
import com.example.proxy.config.v3_proxyfactory.advice.LogTraceAdvice
import com.example.proxy.config.v4_postprocessor.postprocessor.PackageLogTracePostProcessor
import com.example.proxy.trace.logtrace.LogTrace
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.Advisor
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(AppV1Config::class, AppV2Config::class)
class BeanPostProcessorConfig {
    @Bean
    fun logTracePostProcessor(logTrace: LogTrace): PackageLogTracePostProcessor {
        return PackageLogTracePostProcessor("com.example.proxy.app", getAdvisor(logTrace))
    }

    private fun getAdvisor(logTrace: LogTrace): Advisor {
        //pointcut
        val pointcut = NameMatchMethodPointcut()
        pointcut.setMappedNames("request*", "order*", "save*")
        //advice
        val advice = LogTraceAdvice(logTrace)
        return DefaultPointcutAdvisor(pointcut, advice)
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

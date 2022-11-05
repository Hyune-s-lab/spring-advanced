package com.example.proxy.config.v5_autoproxy

import com.example.proxy.config.AppV1Config
import com.example.proxy.config.AppV2Config
import com.example.proxy.config.v3_proxyfactory.advice.LogTraceAdvice
import com.example.proxy.trace.logtrace.LogTrace
import org.springframework.aop.Advisor
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(AppV1Config::class, AppV2Config::class)
class AutoProxyConfig {
    //    @Bean
    fun advisor1(logTrace: LogTrace): Advisor {
        //pointcut
        val pointcut = NameMatchMethodPointcut()
        pointcut.setMappedNames("request*", "order*", "save*")
        //advice
        val advice = LogTraceAdvice(logTrace)
        return DefaultPointcutAdvisor(pointcut, advice)
    }

    //    @Bean
    fun advisor2(logTrace: LogTrace): Advisor {
        //pointcut
        val pointcut = AspectJExpressionPointcut()
        pointcut.expression = "execution(* com.example.proxy.app..*(..))"
        //advice
        val advice = LogTraceAdvice(logTrace)
        return DefaultPointcutAdvisor(pointcut, advice)
    }

    @Bean
    fun advisor3(logTrace: LogTrace): Advisor {
        //pointcut
        val pointcut = AspectJExpressionPointcut()
        pointcut.expression =
            "execution(* com.example.proxy.app..*(..)) && !execution(* com.example.proxy.app..noLog(..))"
        //advice
        val advice = LogTraceAdvice(logTrace)
        return DefaultPointcutAdvisor(pointcut, advice)
    }
}

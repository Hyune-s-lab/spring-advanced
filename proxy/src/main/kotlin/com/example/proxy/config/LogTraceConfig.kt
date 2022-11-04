package com.example.proxy.config

import com.example.proxy.trace.logtrace.LogTrace
import com.example.proxy.trace.logtrace.ThreadLocalLogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LogTraceConfig {
    @Bean
    fun logTrace(): LogTrace {
        return ThreadLocalLogTrace()
    }
}

package com.example.advanced.config

import com.example.advanced.trace.logtrace.FieldLogTrace
import com.example.advanced.trace.logtrace.LogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LogTraceConfig {
    @Bean
    fun logTrace(): LogTrace {
        return FieldLogTrace()
    }
}

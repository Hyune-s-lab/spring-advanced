package com.example.proxy

import com.example.proxy.config.LogTraceConfig
import com.example.proxy.config.v3_proxyfactory.ProxyFactoryConfigV1
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

//@Import(AppV1Config::class)
//@Import(AppV1Config::class, AppV2Config::class)
//@Import(InterfaceProxyConfig::class, LogTraceConfig::class)
//@Import(ConcreteProxyConfig::class, LogTraceConfig::class)
//@Import(DynamicProxyBasicConfig::class, LogTraceConfig::class)
//@Import(DynamicProxyFilterConfig::class, LogTraceConfig::class)
@Import(ProxyFactoryConfigV1::class, LogTraceConfig::class)
@SpringBootApplication(scanBasePackages = ["com.example.proxy.app"]) //주의
class ProxyApplication

fun main(args: Array<String>) {
    runApplication<ProxyApplication>(*args)
}

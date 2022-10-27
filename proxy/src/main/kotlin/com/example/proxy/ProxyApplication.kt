package com.example.proxy

import com.example.proxy.config.AppV1Config
import com.example.proxy.config.AppV2Config
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

//@Import(AppV1Config::class)
@Import(AppV1Config::class, AppV2Config::class)
@SpringBootApplication(scanBasePackages = ["com.example.proxy.app"]) //주의
class ProxyApplication

fun main(args: Array<String>) {
    runApplication<ProxyApplication>(*args)
}

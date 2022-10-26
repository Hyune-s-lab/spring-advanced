package com.example.advanced.trace.strategy.code.template

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TimeLogTemplate {
    fun execute(callback: Callback) {
        val startTime = System.currentTimeMillis()
        //비즈니스 로직 실행
        callback.call() //위임
        //비즈니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime={}", resultTime)
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

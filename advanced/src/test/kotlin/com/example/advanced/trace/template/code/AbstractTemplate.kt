package com.example.advanced.trace.template.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class AbstractTemplate {
    fun execute() {
        val startTime = System.currentTimeMillis()
        //비즈니스 로직 실행
        call() //상속
        //비즈니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime={}", resultTime)
    }

    protected abstract fun call()

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

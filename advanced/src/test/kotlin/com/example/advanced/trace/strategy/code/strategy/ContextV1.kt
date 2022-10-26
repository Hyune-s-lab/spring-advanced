package com.example.advanced.trace.strategy.code.strategy

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * 필드에 전략을 보관하는 방식
 */
class ContextV1(private val strategy: Strategy) {
    fun execute() {
        val startTime = System.currentTimeMillis()
        //비즈니스 로직 실행
        strategy.call() //위임
        //비즈니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime={}", resultTime)
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

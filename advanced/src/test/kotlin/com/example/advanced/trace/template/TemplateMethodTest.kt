package com.example.advanced.trace.template

import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class TemplateMethodTest {
    @Test
    fun templateMethodV0() {
        logic1()
        logic2()
    }

    private fun logic1() {
        val startTime = System.currentTimeMillis()
        //비즈니스 로직 실행
        log.info("비즈니스 로직1 실행")
        //비즈니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime={}", resultTime)
    }

    private fun logic2() {
        val startTime = System.currentTimeMillis()
        //비즈니스 로직 실행
        log.info("비즈니스 로직2 실행")
        //비즈니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime={}", resultTime)
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

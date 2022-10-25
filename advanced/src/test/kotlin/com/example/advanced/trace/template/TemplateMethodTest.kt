package com.example.advanced.trace.template

import com.example.advanced.trace.template.code.AbstractTemplate
import com.example.advanced.trace.template.code.SubClassLogic1
import com.example.advanced.trace.template.code.SubClassLogic2
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


    /**
     * 템플릿 메서드 패턴 적용
     */
    @Test
    fun templateMethodV1() {
        val template1: AbstractTemplate = SubClassLogic1()
        template1.execute()
        val template2: AbstractTemplate = SubClassLogic2()
        template2.execute()
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @Test
    fun templateMethodV2() {
        val template1: AbstractTemplate = object : AbstractTemplate() {
            override fun call() {
                log.info("비즈니스 로직1 실행")
            }
        }
        log.info("클래스 이름1={}", template1::class)
        template1.execute()
        val template2: AbstractTemplate = object : AbstractTemplate() {
            override fun call() {
                log.info("비즈니스 로직2 실행")
            }
        }
        log.info("클래스 이름2={}", template2::class)
        template2.execute()
    }
}

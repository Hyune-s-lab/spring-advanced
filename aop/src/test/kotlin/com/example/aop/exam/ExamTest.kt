package com.example.aop.exam

import com.example.aop.exam.aop.RetryAspect
import com.example.aop.exam.aop.TraceAspect
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

//@Import(TraceAspect::class)
@Import(TraceAspect::class, RetryAspect::class)
@SpringBootTest
class ExamTest {
    @Autowired
    lateinit var examService: ExamService

    @Test
    fun test() {
        for (i in 0..4) {
            log.info("client request i={}", i)
            examService.request("data$i")
        }
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

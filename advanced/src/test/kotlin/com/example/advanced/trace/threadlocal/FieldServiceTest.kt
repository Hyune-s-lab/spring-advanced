package com.example.advanced.trace.threadlocal

import com.example.advanced.trace.threadlocal.code.FieldService
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class FieldServiceTest {

    private val fieldService: FieldService = FieldService()

    @Test
    fun field() {
        log.info("main start")

        val userA = Runnable { fieldService.logic("userA") }
        val userB = Runnable { fieldService.logic("userB") }
        val threadA = Thread(userA)
        threadA.name = "thread-A"
        val threadB = Thread(userB)
        threadB.name = "thread-B"

        threadA.start()
//        sleep(2000) //동시성 문제 발생X
        sleep(100) //동시성 문제 발생O
        threadB.start()
        sleep(3000) //메인 쓰레드 종료 대기
        log.info("main exit")
    }

    private fun sleep(millis: Int) {
        try {
            Thread.sleep(millis.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

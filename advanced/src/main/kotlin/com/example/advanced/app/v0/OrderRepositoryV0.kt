package com.example.advanced.app.v0

import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV0 {
    fun save(itemId: String) {
        //저장 로직
        check(itemId != "ex") { "예외 발생!" }
        sleep(1000)
    }

    private fun sleep(millis: Int) {
        try {
            Thread.sleep(millis.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}

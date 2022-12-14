package com.example.advanced.trace.threadlocal.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class FieldService {
    private var nameStore: String? = null

    fun logic(name: String?): String? {
        log.info("์ ์ฅ name=${name} -> nameStore=${nameStore}")
        nameStore = name
        sleep(1000)
        log.info("์กฐํ nameStore=${nameStore}")
        return nameStore
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

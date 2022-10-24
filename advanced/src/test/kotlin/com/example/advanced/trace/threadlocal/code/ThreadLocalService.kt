package com.example.advanced.trace.threadlocal.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ThreadLocalService {
    private var nameStore: ThreadLocal<String> = ThreadLocal()

    fun logic(name: String?): String? {
        log.info("저장 name=${name} -> nameStore=${nameStore.get()}")
        nameStore.set(name)
        sleep(1000)
        log.info("조회 nameStore=${nameStore.get()}")
        return nameStore.get()
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

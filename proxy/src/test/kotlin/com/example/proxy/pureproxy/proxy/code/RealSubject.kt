package com.example.proxy.pureproxy.proxy.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class RealSubject : Subject {
    override fun operation(): String {
        log.info("실제 객체 호출")
        sleep(1000)
        return "data"
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

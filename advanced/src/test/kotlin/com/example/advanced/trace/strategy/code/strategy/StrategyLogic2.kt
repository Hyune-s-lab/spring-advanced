package com.example.advanced.trace.strategy.code.strategy

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class StrategyLogic2 : Strategy {
    override fun call() {
        log.info("비즈니스 로직2 실행")
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

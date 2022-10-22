package com.example.advanced.trace.hellotrace

import com.example.advanced.trace.TraceId
import com.example.advanced.trace.TraceStatus
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class HelloTraceV2 {
    fun begin(message: String?): TraceStatus {
        val traceId = TraceId()
        val startTimeMs = System.currentTimeMillis()
        val content = addSpace(START_PREFIX, traceId.level)
        log.info("[${traceId.id}] ${content}${message}")
        return TraceStatus(traceId, startTimeMs, message)
    }

    fun beginSync(traceId: TraceId, message: String?): TraceStatus {
        val nextId = traceId.createNextId()
        val startTimeMs = System.currentTimeMillis()
        val content = addSpace(START_PREFIX, nextId.level)
        log.info("[${nextId.id}] ${content}${message}")
        return TraceStatus(nextId, startTimeMs, message)
    }

    fun end(status: TraceStatus) = complete(status, null)
    fun exception(status: TraceStatus, e: Exception) = complete(status, e)

    private fun complete(status: TraceStatus, e: Exception?) {
        val stopTimeMs = System.currentTimeMillis()
        val resultTimeMs = stopTimeMs - status.startTimeMs
        val traceId = status.traceId

        if (e == null) {
            val content = addSpace(COMPLETE_PREFIX, traceId.level)
            log.info("[${traceId.id}] ${content}${status.message} time=${resultTimeMs}ms")
        } else {
            val content = addSpace(EX_PREFIX, traceId.level)
            log.info("[${traceId.id}] ${content}${status.message} time=${resultTimeMs}ms ex=${e.toString()}")
        }
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)

        private const val START_PREFIX = "-->"
        private const val COMPLETE_PREFIX = "<--"
        private const val EX_PREFIX = "<X-"

        private fun addSpace(prefix: String, level: Int): String {
            val sb = StringBuilder()
            (0 until level).forEach { i ->
                sb.append(if (i == level - 1) "|$prefix" else "|   ")
            }
            return sb.toString()
        }
    }
}

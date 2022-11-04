package com.example.proxy.trace.logtrace

import com.example.proxy.trace.TraceId
import com.example.proxy.trace.TraceStatus
import com.example.proxy.trace.logtrace.FieldLogTrace.Companion.log

class ThreadLocalLogTrace : LogTrace {

    private var traceIdHolder: ThreadLocal<TraceId> = ThreadLocal()

    override fun begin(message: String?): TraceStatus {
        syncTraceId()

        val traceId = traceIdHolder.get()
        val startTimeMs = System.currentTimeMillis()
        val content = addSpace(START_PREFIX, traceId.level)
        log.info("[${traceId.id}] ${content}${message}")

        return TraceStatus(traceId, startTimeMs, message)
    }

    override fun end(status: TraceStatus) = complete(status, null)
    override fun exception(status: TraceStatus, e: Exception) = complete(status, e)

    private fun syncTraceId() {
        traceIdHolder.set(traceIdHolder.get()?.createNextId() ?: TraceId())
    }

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

        releaseTraceId()
    }

    private fun releaseTraceId() {
        val traceId = traceIdHolder.get()
        if (traceId.isFirstLevel()) {
            traceIdHolder.remove()
        } else {
            traceIdHolder.set(traceId.createPreviousId())
        }
    }

    companion object {
        private const val START_PREFIX = "-->"
        private const val COMPLETE_PREFIX = "<--"
        private const val EX_PREFIX = "<X-"
        private fun addSpace(prefix: String, level: Int): String {
            val sb = StringBuilder()
            for (i in 0 until level) {
                sb.append(if (i == level - 1) "|$prefix" else "|   ")
            }
            return sb.toString()
        }
    }
}

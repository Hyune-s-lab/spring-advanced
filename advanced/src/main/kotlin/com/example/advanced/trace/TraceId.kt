package com.example.advanced.trace

import java.util.*

class TraceId(
    var id: String,
    var level: Int
) {
    constructor() : this(UUID.randomUUID().toString().substring(0, 8), 0)

    fun createNextId(): TraceId = TraceId(id, level + 1)
    fun createPreviousId(): TraceId = TraceId(id, level - 1)
    fun isFirstLevel(): Boolean = level == 0
}

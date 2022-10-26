package com.example.advanced.trace.callback

interface TraceCallback<T> {
    fun call(): T
}

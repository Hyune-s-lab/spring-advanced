package com.example.proxy.trace.callback

interface TraceCallback<T> {
    fun call(): T
}

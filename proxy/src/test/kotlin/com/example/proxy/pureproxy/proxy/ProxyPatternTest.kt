package com.example.proxy.pureproxy.proxy

import com.example.proxy.pureproxy.proxy.code.ProxyPatternClient
import com.example.proxy.pureproxy.proxy.code.RealSubject
import org.junit.jupiter.api.Test

class ProxyPatternTest {
    @Test
    fun noProxyTest() {
        val realSubject = RealSubject()
        val client = ProxyPatternClient(realSubject)
        client.execute()
        client.execute()
        client.execute()
    }
}

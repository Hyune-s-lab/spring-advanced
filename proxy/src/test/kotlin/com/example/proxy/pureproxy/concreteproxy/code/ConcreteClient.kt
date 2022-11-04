package com.example.proxy.pureproxy.concreteproxy.code

class ConcreteClient(private val concreteLogic: ConcreteLogic) {
    fun execute() {
        concreteLogic.operation()
    }
}

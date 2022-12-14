package com.example.proxy.app.v1

open class OrderRepositoryV1Impl : OrderRepositoryV1 {
    override fun save(itemId: String) {
        //저장 로직
        check(itemId != "ex") { "예외 발생!" }
        sleep(1000)
    }

    private fun sleep(millis: Int) {
        try {
            Thread.sleep(millis.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}

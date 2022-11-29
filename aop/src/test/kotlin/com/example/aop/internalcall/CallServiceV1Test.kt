package com.example.aop.internalcall

import com.example.aop.internalcall.aop.CallLogAspect
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(CallLogAspect::class)
@SpringBootTest
internal class CallServiceV1Test {
//    @Autowired
//    lateinit var callServiceV1: CallServiceV1
//
//    @Test
//    fun external() {
//        callServiceV1.external()
//    }
}

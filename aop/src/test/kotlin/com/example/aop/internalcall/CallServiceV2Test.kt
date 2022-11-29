package com.example.aop.internalcall

import com.example.aop.internalcall.aop.CallLogAspect
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(CallLogAspect::class)
@SpringBootTest
internal class CallServiceV2Test {
//    @Autowired
//    lateinit var callServiceV2: CallServiceV2
//
//    @Test
//    fun external() {
//        callServiceV2.external()
//    }
}

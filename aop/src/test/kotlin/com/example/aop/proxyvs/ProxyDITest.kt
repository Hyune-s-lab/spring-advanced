package com.example.aop.proxyvs

import com.example.aop.member.MemberService
import com.example.aop.member.MemberServiceImpl
import com.example.aop.proxyvs.code.ProxyDIAspect
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

//@SpringBootTest(properties = ["spring.aop.proxy-target-class=false"]) //JDK 동적 프록시
//@SpringBootTest(properties = ["spring.aop.proxy-target-class=true"]) //CGLIB 프록시
@SpringBootTest
@Import(ProxyDIAspect::class)
class ProxyDITest {
    @Autowired
    lateinit var memberService: MemberService

    @Autowired
    lateinit var memberServiceImpl: MemberServiceImpl

    @Test
    fun go() {
        log.info("memberService class={}", memberService.javaClass)
        log.info("memberServiceImpl class={}", memberServiceImpl.javaClass)
        memberServiceImpl.hello("hello")
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

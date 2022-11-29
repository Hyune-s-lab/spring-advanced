package com.example.aop.proxyvs

import com.example.aop.member.MemberService
import com.example.aop.member.MemberServiceImpl
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.framework.ProxyFactory

class ProxyCastingTest {
    @Test
    fun jdkProxy() {
        val target = MemberServiceImpl()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.isProxyTargetClass = false //JDK 동적 프록시

        //프록시를 인터페이스로 캐스팅 성공
        val memberServiceProxy: MemberService = proxyFactory.proxy as MemberService

        //JDK 동적 프록시를 구현 클래스로 캐스팅 시도 실패, ClassCastException 예외 발생
        assertThrows(ClassCastException::class.java) {
            val castingMemberService: MemberServiceImpl = memberServiceProxy as MemberServiceImpl
        }
    }

    @Test
    fun cglibProxy() {
        val target = MemberServiceImpl()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.isProxyTargetClass = true //CGLIB 프록시

        //프록시를 인터페이스로 캐스팅 성공
        val memberServiceProxy: MemberService = proxyFactory.proxy as MemberService
        log.info("proxy class={}", memberServiceProxy.javaClass)

        //CGLIB 프록시를 구현 클래스로 캐스팅 시도 성공
        val castingMemberService: MemberServiceImpl = memberServiceProxy as MemberServiceImpl
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

package com.example.aop.pointcut

import com.example.aop.member.MemberService
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(AtAnnotationTest.AtAnnotationAspect::class)
@SpringBootTest
class AtAnnotationTest {
    @Autowired
    lateinit var memberService: MemberService

    @Test
    fun success() {
        log.info("memberService Proxy={}", memberService::class)
        memberService.hello("helloA")
    }

    @Aspect
    internal class AtAnnotationAspect {
        @Around("@annotation(hello.aop.member.annotation.MethodAop)")
        @Throws(Throwable::class)
        fun doAtAnnotation(joinPoint: ProceedingJoinPoint): Any {
            log.info("[@annotation] {}", joinPoint.signature)
            return joinPoint.proceed()
        }
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

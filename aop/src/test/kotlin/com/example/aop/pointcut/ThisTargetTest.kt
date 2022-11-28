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

/**
 * application.properties
 * spring.aop.proxy-target-class=true  CGLIB
 * spring.aop.proxy-target-class=false JDK 동적 프록시
 */
@Import(ThisTargetTest.ThisTargetAspect::class) //@SpringBootTest(properties = "spring.aop.proxy-target-class=false") //JDK 동적 프록시
@SpringBootTest(properties = ["spring.aop.proxy-target-class=true"]) //CGLIB
class ThisTargetTest {
    @Autowired
    lateinit var memberService: MemberService

    @Test
    fun success() {
        log.info("memberService Proxy={}", memberService::class.java)
        memberService.hello("helloA")
    }

    @Aspect
    internal class ThisTargetAspect {
        //부모 타입 허용
        @Around("this(com.example.aop.member.MemberService)")
        @Throws(Throwable::class)
        fun doThisInterface(joinPoint: ProceedingJoinPoint): Any {
            log.info("[this-interface] {}", joinPoint.signature)
            return joinPoint.proceed()
        }

        //부모 타입 허용
        @Around("target(com.example.aop.member.MemberService)")
        @Throws(Throwable::class)
        fun doTargetInterface(joinPoint: ProceedingJoinPoint): Any {
            log.info("[target-interface] {}", joinPoint.signature)
            return joinPoint.proceed()
        }

        @Around("this(com.example.aop.member.MemberServiceImpl)")
        @Throws(Throwable::class)
        fun doThis(joinPoint: ProceedingJoinPoint): Any {
            log.info("[this-impl] {}", joinPoint.signature)
            return joinPoint.proceed()
        }

        @Around("target(com.example.aop.member.MemberServiceImpl)")
        @Throws(Throwable::class)
        fun doTarget(joinPoint: ProceedingJoinPoint): Any {
            log.info("[target-impl] {}", joinPoint.signature)
            return joinPoint.proceed()
        }
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

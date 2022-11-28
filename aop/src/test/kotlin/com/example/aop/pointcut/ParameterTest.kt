package com.example.aop.pointcut

import com.example.aop.member.MemberService
import com.example.aop.member.annotation.ClassAop
import com.example.aop.member.annotation.MethodAop
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(ParameterTest.ParameterAspect::class)
@SpringBootTest
class ParameterTest {
    @Autowired
    lateinit var memberService: MemberService

    @Test
    fun success() {
        log.info("memberService Proxy={}", memberService::class.java)
        memberService.hello("helloA")
    }

    @Aspect
    internal class ParameterAspect {
        @Pointcut("execution(* com.example.aop.member..*.*(..))")
        private fun allMember() {
        }

        @Around("allMember()")
        @Throws(Throwable::class)
        fun logArgs1(joinPoint: ProceedingJoinPoint): Any? {
            val arg1: Any = joinPoint.args[0]
            log.info("[logArgs1]{}, arg={}", joinPoint.signature, arg1)
            return joinPoint.proceed()
        }

        @Around("allMember() && args(arg,..)")
        @Throws(Throwable::class)
        fun logArgs2(joinPoint: ProceedingJoinPoint, arg: Any?): Any? {
            log.info("[logArgs2]{}, arg={}", joinPoint.signature, arg)
            return joinPoint.proceed()
        }

        @Before("allMember() && args(arg,..)")
        fun logArgs3(arg: String?) {
            log.info("[logArgs3] arg={}", arg)
        }

        @Before("allMember() && this(obj)")
        fun thisArgs(joinPoint: JoinPoint, obj: MemberService) {
            log.info("[this]{}, obj={}", joinPoint.signature, obj::class.java)
        }

        @Before("allMember() && target(obj)")
        fun targetArgs(joinPoint: JoinPoint, obj: MemberService) {
            log.info("[target]{}, obj={}", joinPoint.signature, obj::class.java)
        }

        @Before("allMember() && @target(annotation)")
        fun atTarget(joinPoint: JoinPoint, annotation: ClassAop?) {
            log.info("[@target]{}, obj={}", joinPoint.signature, annotation)
        }

        @Before("allMember() && @within(annotation)")
        fun atWithin(joinPoint: JoinPoint, annotation: ClassAop?) {
            log.info("[@within]{}, obj={}", joinPoint.signature, annotation)
        }

        @Before("allMember() && @annotation(annotation)")
        fun atAnnotation(joinPoint: JoinPoint, annotation: MethodAop) {
            log.info("[@annotation]{}, annotationValue={}", joinPoint.signature, annotation.value)
        }
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

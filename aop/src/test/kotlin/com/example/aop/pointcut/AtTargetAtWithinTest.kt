package com.example.aop.pointcut

import com.example.aop.member.annotation.ClassAop
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

@Import(AtTargetAtWithinTest.Config::class)
@SpringBootTest
class AtTargetAtWithinTest {

    @Autowired
    lateinit var child: Child

    @Test
    fun success() {
        log.info("child Proxy={}", child.javaClass)
        child.childMethod() //부모, 자식 모두 있는 메서드
        child.parentMethod() //부모 클래스만 있는 메서드
    }

    internal class Config {
        @Bean
        fun parent(): Parent {
            return Parent()
        }

        @Bean
        fun child(): Child {
            return Child()
        }

        @Bean
        fun atTargetAtWithinAspect(): AtTargetAtWithinAspect {
            return AtTargetAtWithinAspect()
        }
    }

    open class Parent {
        fun parentMethod() {} //부모에만 있는 메서드
    }

    @ClassAop
    class Child : Parent() {
        fun childMethod() {}
    }

    @Aspect
    internal class AtTargetAtWithinAspect {
        //@target: 인스턴스 기준으로 모든 메서드의 조인 포인트를 선정, 부모 타입의 메서드도 적용
        @Around("execution(* hello.aop..*(..)) && @target(hello.aop.member.annotation.ClassAop)")
        @Throws(Throwable::class)
        fun atTarget(joinPoint: ProceedingJoinPoint): Any {
            log.info("[@target] {}", joinPoint.signature)
            return joinPoint.proceed()
        }

        //@within: 선택된 클래스 내부에 있는 메서드만 조인 포인트로 선정, 부모 타입의 메서드는 적용되지 않음
        @Around("execution(* hello.aop..*(..)) && @within(hello.aop.member.annotation.ClassAop)")
        @Throws(Throwable::class)
        fun atWithin(joinPoint: ProceedingJoinPoint): Any {
            log.info("[@within] {}", joinPoint.signature)
            return joinPoint.proceed()
        }
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

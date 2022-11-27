package com.example.aop.pointcut

import com.example.aop.member.MemberServiceImpl
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import java.lang.reflect.Method

class ExecutionTest {
    var pointcut: AspectJExpressionPointcut = AspectJExpressionPointcut()
    lateinit var helloMethod: Method

    @BeforeEach
    @Throws(NoSuchMethodException::class)
    fun init() {
        helloMethod = MemberServiceImpl::class.java.getMethod("hello", String::class.java)
    }

    @Test
    fun printMethod() {
        //public java.lang.String com.example.aop.member.MemberServiceImpl.hello(java.lang.String)
        log.info("helloMethod={}", helloMethod)
    }

    @Test
    fun exactMatch() {
        //public java.lang.String com.example.aop.member.MemberServiceImpl.hello(java.lang.String)
        pointcut.expression = "execution(public String com.example.aop.member.MemberServiceImpl.hello(String))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    fun allMatch() {
        pointcut.expression = "execution(* *(..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    fun nameMatch() {
        pointcut.expression = "execution(* hello(..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    fun nameMatchStar1() {
        pointcut.expression = "execution(* hel*(..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    fun nameMatchStar2() {
        pointcut.expression = "execution(* *el*(..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    fun nameMatchFalse() {
        pointcut.expression = "execution(* nono(..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isFalse
    }

    @Test
    fun packageExactMatch1() {
        pointcut.expression = "execution(* com.example.aop.member.MemberServiceImpl.hello(..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    fun packageExactMatch2() {
        pointcut.expression = "execution(* com.example.aop.member.*.*(..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    fun packageExactFalse() {
        pointcut.expression = "execution(* com.example.aop.*.*(..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isFalse
    }

    @Test
    fun packageMatchSubPackage1() {
        pointcut.expression = "execution(* com.example.aop.member..*.*(..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    fun packageMatchSubPackage2() {
        pointcut.expression = "execution(* com.example.aop..*.*(..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    fun typeExactMatch() {
        pointcut.expression = "execution(* com.example.aop.member.MemberServiceImpl.*(..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    fun typeMatchSuperType() {
        pointcut.expression = "execution(* com.example.aop.member.MemberService.*(..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    @Throws(NoSuchMethodException::class)
    fun typeMatchInternal() {
        pointcut.expression = "execution(* com.example.aop.member.MemberServiceImpl.*(..))"
        val internalMethod: Method = MemberServiceImpl::class.java.getMethod("internal", String::class.java)
        assertThat(pointcut.matches(internalMethod, MemberServiceImpl::class.java)).isTrue
    }

    @Test
    @Throws(NoSuchMethodException::class)
    fun typeMatchNoSuperTypeMethodFalse() {
        pointcut.expression = "execution(* com.example.aop.member.MemberService.*(..))"
        val internalMethod: Method = MemberServiceImpl::class.java.getMethod("internal", String::class.java)
        assertThat(pointcut.matches(internalMethod, MemberServiceImpl::class.java)).isFalse
    }

    //String 타입의 파라미터 허용
    //(String)
    @Test
    fun argsMatch() {
        pointcut.expression = "execution(* *(String))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    //파라미터가 없어야 함
    //()
    @Test
    fun argsMatchNoArgs() {
        pointcut.expression = "execution(* *())"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isFalse
    }

    //정확히 하나의 파라미터 허용, 모든 타입 허용
    //(Xxx)
    @Test
    fun argsMatchStar() {
        pointcut.expression = "execution(* *(*))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    //숫자와 무관하게 모든 파라미터, 모든 타입 허용
    //(), (Xxx), (Xxx, Xxx)
    @Test
    fun argsMatchAll() {
        pointcut.expression = "execution(* *(..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    //String 타입으로 시작, 숫자와 무관하게 모든 파라미터, 모든 타입 허용
    //(String), (String, Xxx), (String, Xxx, Xxx)
    @Test
    fun argsMatchComplex() {
        pointcut.expression = "execution(* *(String, ..))"
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}

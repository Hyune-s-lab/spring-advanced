package com.example.aop.pointcut

import com.example.aop.member.MemberServiceImpl
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import java.lang.reflect.Method

class ArgsTest {
    lateinit var helloMethod: Method

    @BeforeEach
    @Throws(NoSuchMethodException::class)
    fun init() {
        helloMethod = MemberServiceImpl::class.java.getMethod("hello", String::class.java)
    }

    private fun pointcut(expression: String): AspectJExpressionPointcut {
        val pointcut = AspectJExpressionPointcut()
        pointcut.expression = expression
        return pointcut
    }

    @Test
    fun args() {
        //hello(String)과 매칭
        assertThat(pointcut("args(String)")
            .matches(helloMethod, MemberServiceImpl::class.java)).isTrue
        assertThat(pointcut("args(Object)")
            .matches(helloMethod, MemberServiceImpl::class.java)).isTrue
        assertThat(pointcut("args()")
            .matches(helloMethod, MemberServiceImpl::class.java)).isFalse
        assertThat(pointcut("args(..)")
            .matches(helloMethod, MemberServiceImpl::class.java)).isTrue
        assertThat(pointcut("args(*)")
            .matches(helloMethod, MemberServiceImpl::class.java)).isTrue
        assertThat(pointcut("args(String,..)")
            .matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    }

    /**
     * execution(* *(java.io.Serializable)): 메서드의 시그니처로 판단 (정적)
     * args(java.io.Serializable): 런타임에 전달된 인수로 판단 (동적)
     */
    @Test
    fun argsVsExecution() {
        //Args
        assertThat(pointcut("args(String)")
            .matches(helloMethod, MemberServiceImpl::class.java)).isTrue
        assertThat(pointcut("args(java.io.Serializable)")
            .matches(helloMethod, MemberServiceImpl::class.java)).isTrue
        assertThat(pointcut("args(Object)")
            .matches(helloMethod, MemberServiceImpl::class.java)).isTrue

        //Execution
        assertThat(pointcut("execution(* *(String))")
            .matches(helloMethod, MemberServiceImpl::class.java)).isTrue
        assertThat(pointcut("execution(* *(java.io.Serializable))") //매칭 실패
            .matches(helloMethod, MemberServiceImpl::class.java)).isFalse
        assertThat(pointcut("execution(* *(Object))") //매칭 실패
            .matches(helloMethod, MemberServiceImpl::class.java)).isFalse
    }
}

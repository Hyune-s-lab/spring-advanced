package com.example.aop.order.aop

import org.aspectj.lang.annotation.Pointcut

class Pointcuts {
    @Pointcut("execution(* com.example.aop.order..*(..))")
    fun allOrder() {
    } //pointcut signature

    //클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    fun allService() {
    }

    //allOrder && allService
    @Pointcut("allOrder() && allService()")
    fun orderAndService() {
    }
}

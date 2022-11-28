package com.example.aop.exam

import com.example.aop.exam.annotation.Retry
import com.example.aop.exam.annotation.Trace
import org.springframework.stereotype.Repository

@Repository
class ExamRepository {
    /**
     * 5번에 1번 실패하는 요청
     */
    @Trace
    @Retry(value = 4)
    fun save(itemId: String?): String {
        seq++
        check(seq % 5 != 0) { "예외 발생" }
        return "ok"
    }

    companion object {
        private var seq = 0
    }
}

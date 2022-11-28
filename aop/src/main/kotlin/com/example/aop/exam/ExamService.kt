package com.example.aop.exam

import com.example.aop.exam.annotation.Trace
import org.springframework.stereotype.Service

@Service
class ExamService(private val examRepository: ExamRepository) {
    @Trace
    fun request(itemId: String?) {
        examRepository.save(itemId)
    }
}

package com.example.myapplication.domain.usecase


import com.example.myapplication.domain.model.CalculationResult
import com.example.myapplication.domain.repository.CalculatorRepository

class MultiplyUseCase(private val repository: CalculatorRepository) {
    operator fun invoke(a: Double, b: Double): CalculationResult {
        return repository.multiply(a, b)
    }
}
package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.CalculationResult

interface CalculatorRepository {
    fun add (a: Double, b: Double):
            CalculationResult
    fun subtract(a: Double, b: Double):
            CalculationResult
    fun multiply(a: Double, b: Double):
            CalculationResult
    fun divide(a: Double, b: Double):
            CalculationResult
}
package com.example.myapplication.data.repository

import com.example.myapplication.domain.model.CalculationResult
import com.example.myapplication.domain.repository.CalculatorRepository

class CalculatorRepositoryImpl : CalculatorRepository {

    override fun add(a: Double, b: Double): CalculationResult {
        return try {
            CalculationResult(value = a + b)
        } catch (e: Exception) {
            CalculationResult(
                value = 0.0,
                isError = true,
                errorMessage = "Error en suma: ${e.message}"
            )
        }
    }

    override fun subtract(a: Double, b: Double): CalculationResult {
        return try {
            CalculationResult(value = a - b)
        } catch (e: Exception) {
            CalculationResult(
                value = 0.0,
                isError = true,
                errorMessage = "Error en resta: ${e.message}"
            )
        }
    }

    override fun multiply(a: Double, b: Double): CalculationResult {
        return try {
            CalculationResult(value = a * b)
        } catch (e: Exception) {
            CalculationResult(
                value = 0.0,
                isError = true,
                errorMessage = "Error en multiplicación: ${e.message}"
            )
        }
    }

    override fun divide(a: Double, b: Double): CalculationResult {
        return try {
            if (b == 0.0) {
                CalculationResult(
                    value = 0.0,
                    isError = true,
                    errorMessage = "No se puede dividir por cero"
                )
            } else {
                CalculationResult(value = a / b)
            }
        } catch (e: Exception){
            CalculationResult(
                value = 0.0,
                isError = true,
                errorMessage = "Error en la division: ${e.message}"
            )
        }
    }
}


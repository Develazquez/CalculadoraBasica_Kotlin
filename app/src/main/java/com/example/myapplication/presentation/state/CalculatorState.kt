package com.example.myapplication.presentation.state

data class CalculatorState(
    val displayValue: String = "0",
    val firstOperand: Double? = null,
    val operation: Operation? = null,
    val isNewNumber: Boolean = true,
    val errorMessage: String? = null
)

enum class Operation {
    ADD, SUBTRACT, MULTIPLY, DIVIDE
}
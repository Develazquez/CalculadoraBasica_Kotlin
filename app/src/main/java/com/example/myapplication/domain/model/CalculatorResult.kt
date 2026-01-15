package com.example.myapplication.domain.model

data class CalculationResult (
    val value: Double,
    val isError: Boolean = false,
    val errorMessage: String? = null
)
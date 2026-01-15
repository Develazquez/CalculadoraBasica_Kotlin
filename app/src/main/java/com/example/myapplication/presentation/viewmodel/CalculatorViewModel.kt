package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.CalculatorRepositoryImpl
import com.example.myapplication.domain.usecase.AddUseCase
import com.example.myapplication.domain.usecase.SubtractUseCase
import com.example.myapplication.domain.usecase.MultiplyUseCase
import com.example.myapplication.domain.usecase.DivideUseCase
import com.example.myapplication.presentation.state.CalculatorState
import com.example.myapplication.presentation.state.Operation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CalculatorViewModel : ViewModel() {

    private val repository = CalculatorRepositoryImpl()
    private val addUseCase = AddUseCase(repository)
    private val subtractUseCase = SubtractUseCase(repository)
    private val multiplyUseCase = MultiplyUseCase(repository)
    private val divideUseCase = DivideUseCase(repository)

    private val _state = MutableStateFlow(CalculatorState())
    val state: StateFlow<CalculatorState> = _state.asStateFlow()

    fun onNumberClick(number: String) {
        viewModelScope.launch {
            val currentDisplay = _state.value.displayValue
            val newDisplay = if (_state.value.isNewNumber) {
                if (number == ".") "0." else number
            } else {
                if (number == "." && currentDisplay.contains(".")) {
                    currentDisplay
                } else {
                    currentDisplay + number
                }
            }

            _state.value = _state.value.copy(
                displayValue = newDisplay,
                isNewNumber = false,
                errorMessage = null
            )
        }
    }

    fun onOperationClick(operation: Operation) {
        viewModelScope.launch {
            val currentValue = _state.value.displayValue.toDoubleOrNull() ?: 0.0

            if (_state.value.firstOperand != null && _state.value.operation != null && !_state.value.isNewNumber) {
                calculate()
            }

            _state.value = _state.value.copy(
                firstOperand = if (_state.value.errorMessage == null) {
                    _state.value.displayValue.toDoubleOrNull() ?: currentValue
                } else {
                    currentValue
                },
                operation = operation,
                isNewNumber = true,
                errorMessage = null
            )
        }
    }

    fun onEqualsClick() {
        viewModelScope.launch {
            calculate()
        }
    }

    private fun calculate() {
        val firstOperand = _state.value.firstOperand ?: return
        val secondOperand = _state.value.displayValue.toDoubleOrNull() ?: return
        val operation = _state.value.operation ?: return

        val result = when (operation) {
            Operation.ADD -> addUseCase(firstOperand, secondOperand)
            Operation.SUBTRACT -> subtractUseCase(firstOperand, secondOperand)
            Operation.MULTIPLY -> multiplyUseCase(firstOperand, secondOperand)
            Operation.DIVIDE -> divideUseCase(firstOperand, secondOperand)
        }

        _state.value = if (result.isError) {
            _state.value.copy(
                errorMessage = result.errorMessage,
                isNewNumber = true
            )
        } else {
            _state.value.copy(
                displayValue = formatResult(result.value),
                firstOperand = null,
                operation = null,
                isNewNumber = true,
                errorMessage = null
            )
        }
    }

    fun onClearClick() {
        viewModelScope.launch {
            _state.value = CalculatorState()
        }
    }

    fun onDeleteClick() {
        viewModelScope.launch {
            val currentDisplay = _state.value.displayValue
            val newDisplay = if (currentDisplay.length > 1) {
                currentDisplay.dropLast(1)
            } else {
                "0"
            }

            _state.value = _state.value.copy(
                displayValue = newDisplay
            )
        }
    }

    private fun formatResult(value: Double): String {
        return if (value % 1.0 == 0.0) {
            value.toLong().toString()
        } else {
            String.format("%.8f", value).trimEnd('0').trimEnd('.')
        }
    }
}
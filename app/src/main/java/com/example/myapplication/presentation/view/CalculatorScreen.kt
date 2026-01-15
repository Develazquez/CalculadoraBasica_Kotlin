package com.example.myapplication.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.presentation.viewmodel.CalculatorViewModel
import com.example.myapplication.presentation.state.Operation
import com.example.myapplication.ui.theme.BackgroundColor
import com.example.myapplication.ui.theme.ButtonTextColor
import com.example.myapplication.ui.theme.EqualsButtonColor
import com.example.myapplication.ui.theme.ErrorColor
import com.example.myapplication.ui.theme.FunctionButtonColor
import com.example.myapplication.ui.theme.NumberButtonColor
import com.example.myapplication.ui.theme.OperationButtonColor
import com.example.myapplication.ui.theme.DisplayTextColor

@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Display
        DisplaySection(
            displayValue = state.displayValue,
            errorMessage = state.errorMessage,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )

        // Buttons
        ButtonsSection(
            onNumberClick = { viewModel.onNumberClick(it) },
            onOperationClick = { viewModel.onOperationClick(it) },
            onEqualsClick = { viewModel.onEqualsClick() },
            onClearClick = { viewModel.onClearClick() },
            onDeleteClick = { viewModel.onDeleteClick() }
        )
    }
}

@Composable
fun DisplaySection(
    displayValue: String,
    errorMessage: String?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        if (errorMessage != null) {
            Text(
                text = errorMessage,
                fontSize = 18.sp,
                color = ErrorColor,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Text(
            text = displayValue,
            fontSize = 64.sp,
            fontWeight = FontWeight.Light,
            color = DisplayTextColor,
            textAlign = TextAlign.End,
            maxLines = 2,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ButtonsSection(
    onNumberClick: (String) -> Unit,
    onOperationClick: (Operation) -> Unit,
    onEqualsClick: () -> Unit,
    onClearClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Primera fila: C, DEL, /, ×
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            CalculatorButton(
                text = "C",
                onClick = onClearClick,
                backgroundColor = FunctionButtonColor,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = "DEL",
                onClick = onDeleteClick,
                backgroundColor = FunctionButtonColor,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = "/",
                onClick = { onOperationClick(Operation.DIVIDE) },
                backgroundColor = OperationButtonColor,
                modifier = Modifier.weight(1f)
            )
            CalculatorButton(
                text = "×",
                onClick = { onOperationClick(Operation.MULTIPLY) },
                backgroundColor = OperationButtonColor,
                modifier = Modifier.weight(1f)
            )
        }

        // Segunda fila: 7, 8, 9, -
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            CalculatorButton("7", { onNumberClick("7") }, modifier = Modifier.weight(1f))
            CalculatorButton("8", { onNumberClick("8") }, modifier = Modifier.weight(1f))
            CalculatorButton("9", { onNumberClick("9") }, modifier = Modifier.weight(1f))
            CalculatorButton(
                text = "-",
                onClick = { onOperationClick(Operation.SUBTRACT) },
                backgroundColor = OperationButtonColor,
                modifier = Modifier.weight(1f)
            )
        }

        // Tercera fila: 4, 5, 6, +
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            CalculatorButton("4", { onNumberClick("4") }, modifier = Modifier.weight(1f))
            CalculatorButton("5", { onNumberClick("5") }, modifier = Modifier.weight(1f))
            CalculatorButton("6", { onNumberClick("6") }, modifier = Modifier.weight(1f))
            CalculatorButton(
                text = "+",
                onClick = { onOperationClick(Operation.ADD) },
                backgroundColor = OperationButtonColor,
                modifier = Modifier.weight(1f)
            )
        }

        // Cuarta fila: 1, 2, 3, =
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            CalculatorButton("1", { onNumberClick("1") }, modifier = Modifier.weight(1f))
            CalculatorButton("2", { onNumberClick("2") }, modifier = Modifier.weight(1f))
            CalculatorButton("3", { onNumberClick("3") }, modifier = Modifier.weight(1f))
            CalculatorButton(
                text = "=",
                onClick = onEqualsClick,
                backgroundColor = EqualsButtonColor,
                modifier = Modifier.weight(1f)
            )
        }

        // Quinta fila: 0, .
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            CalculatorButton(
                text = "0",
                onClick = { onNumberClick("0") },
                modifier = Modifier.weight(2f)
            )
            CalculatorButton(
                text = ".",
                onClick = { onNumberClick(".") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun CalculatorButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: androidx.compose.ui.graphics.Color = NumberButtonColor
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .aspectRatio(1f)
            .height(80.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        shape = CircleShape
    ) {
        Text(
            text = text,
            fontSize = 28.sp,
            fontWeight = FontWeight.Medium,
            color = ButtonTextColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorButtonPreview() {
    CalculatorButton(
        text = "5",
        onClick = {}
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalculatorScreenPreview() {
    CalculatorScreen()
}
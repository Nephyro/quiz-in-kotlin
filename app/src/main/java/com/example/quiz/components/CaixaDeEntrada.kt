package com.example.quiz.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CaixaDeEntrada(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    errorMessage: String? = null    // se for nulo, não tem erro
) {
    val isError = errorMessage != null  // Indica se o campo está em estado de erro com base na presença de uma mensagem

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        isError = isError,
        supportingText = {
            if (isError) {
                Text(text = errorMessage!!, color = Color.Red, fontSize = 12.sp)
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedLabelColor = Color.Blue,
            focusedBorderColor = Color.Blue,
            focusedTextColor = Color.Black,
            errorTextColor = Color.Black
        )

    )

}
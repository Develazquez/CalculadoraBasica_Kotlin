package com.example.myapplication.login.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.R

@Composable
fun LoginScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
    var username by remember { mutableStateOf("") }
        Surface() {
            Text(text = "LoginScreen")
        }
        Column() {
            Image(painter = painterResource(id= R.drawable.baseline_10k_24), contentDescription = "logo empresa")
           // BasicTextField() ningun contorno a la salida
           // TextField() solo subrayado
           // OutlinedTextField() marco completo
            TextField(
                value = username,
                onValueChange = {username = it},
                placeholder = {Text("Username")}
            )
            TextField()
            Button() { }
        }
        Text("@Mi impresa")
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}

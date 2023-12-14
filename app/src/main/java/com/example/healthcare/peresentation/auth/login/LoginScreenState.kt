package com.example.healthcare.peresentation.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun rememberLoginScreenState() : LoginScreenState {
    return remember {
        LoginScreenState()
    }
}
class LoginScreenState {
    var phoneNumber by mutableStateOf("")
    var fullPhoneNumber by mutableStateOf("")
}
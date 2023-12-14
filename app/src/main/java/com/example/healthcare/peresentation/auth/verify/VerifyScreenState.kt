package com.example.healthcare.peresentation.auth.verify

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun rememberVerifyState() : VerifyScreenState {
    return remember {
        VerifyScreenState()
    }
}

class VerifyScreenState {
    var otpCode by mutableStateOf("")
}
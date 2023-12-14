package com.example.healthcare.peresentation.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun rememberPatientProfileState() : PatientProfileState {
    return remember {
        PatientProfileState()
    }
}

class PatientProfileState {
    var age by mutableStateOf("")
    var weight by mutableStateOf("")
    var height by mutableStateOf("")
    var medicalHistory by mutableStateOf("")
}
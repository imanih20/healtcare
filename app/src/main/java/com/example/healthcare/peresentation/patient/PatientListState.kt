package com.example.healthcare.peresentation.patient

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun rememberPatientListState() : PatientListState{
    return remember {
        PatientListState()
    }
}

class PatientListState {
    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var address by mutableStateOf("")
    var city by mutableStateOf("")
    var postalCode by mutableStateOf("")
    var age by mutableStateOf("")
    var phone by mutableStateOf("")
    var fullPhoneNumber by mutableStateOf("")
    var height by mutableStateOf("")
    var weight by mutableStateOf("")
    var medicalHistory by mutableStateOf("")

    fun isFilled() = firstName.isNotEmpty() && lastName.isNotEmpty() && address.isNotEmpty() && city.isNotEmpty()
            && postalCode.isNotEmpty() && fullPhoneNumber.isNotEmpty()
}
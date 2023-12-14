package com.example.healthcare.peresentation.auth.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.healthcare.peresentation.auth.utils.Role


@Composable
fun rememberSignScreenState() : SignScreenState{
    return remember {
        SignScreenState()
    }
}


class SignScreenState {
    var fullPhoneNumber by mutableStateOf("")
    var selectedRole by mutableStateOf(Role.Admin)

    //user info page state
    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var address by mutableStateOf("")
    var phone by mutableStateOf("")
    var city by mutableStateOf("")
    var postalCode by mutableStateOf("")

    fun isFieldsFull() =
        firstName.isNotBlank()
                && lastName.isNotBlank()
                && address.isNotBlank()
                && fullPhoneNumber.isNotBlank()
                && city.isNotBlank()
                && postalCode.isNotBlank()
}
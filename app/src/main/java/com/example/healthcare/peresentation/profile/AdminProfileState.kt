package com.example.healthcare.peresentation.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun rememberAdminProfileState() : AdminProfileState {
    return remember {
        AdminProfileState()
    }
}

class AdminProfileState {
    var clinicName by mutableStateOf("")
    var clinicAddress by mutableStateOf("")
    var clinicCity by mutableStateOf("")
    var clinicDescription by mutableStateOf("")
}
package com.example.healthcare.peresentation.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun rememberDoctorProfileState() : DoctorProfileState{
    return remember {
        DoctorProfileState()
    }

}

class DoctorProfileState {
    var clinicID by mutableStateOf("")
    var degree by mutableStateOf("")
    var speciality by mutableStateOf("")
    var description by mutableStateOf("")
}
package com.example.healthcare.peresentation.doctor.viewModels

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun rememberDoctorScreenState() : DoctorScreenState {
    return remember {
        DoctorScreenState()
    }
}

class DoctorScreenState {
    var selectedTab by mutableIntStateOf(0)
}
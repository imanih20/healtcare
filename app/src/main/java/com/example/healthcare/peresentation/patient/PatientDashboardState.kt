package com.example.healthcare.peresentation.patient

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.healthcare.domain.profile.models.PatientProfile

@Composable
fun rememberPatientDashboardState() : PatientDashboardState{
    return remember {
        PatientDashboardState()
    }
}

class PatientDashboardState() {
    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var city by mutableStateOf("")
    var address by mutableStateOf("")
    var postalCode by mutableStateOf("")
    var age by mutableStateOf("")
    var height by mutableStateOf("")
    var weight by mutableStateOf("")
    var medicalHistory by mutableStateOf("")

    fun init(patientProfile: PatientProfile){
        firstName = patientProfile.firstName
        lastName = patientProfile.lastName
        city = patientProfile.city
        address = patientProfile.address
        postalCode = patientProfile.postalCode
        age = patientProfile.age.toString()
        height = patientProfile.height.toString()
        weight = patientProfile.weight.toString()
        medicalHistory = patientProfile.medicalHistory
    }
    fun checkIfEdited(profile: PatientProfile) = firstName != profile.firstName || lastName != profile.lastName || city!= profile.city || address != profile.address || postalCode!= profile.postalCode ||
            age!= profile.age.toString() || height!= profile.height.toString() || weight!= profile.weight.toString() ||
            medicalHistory!= profile.medicalHistory
}
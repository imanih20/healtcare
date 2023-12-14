package com.example.healthcare.peresentation.doctor.viewModels

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.healthcare.domain.profile.models.AdminProfile
import com.example.healthcare.domain.profile.models.DoctorProfile

@Composable
fun rememberDoctorDashboardState() : DoctorDashboardState {
    return remember {
        DoctorDashboardState()
    }
}

class DoctorDashboardState() {
    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var address by mutableStateOf("")
    var city by mutableStateOf("")
    var postalCode by mutableStateOf("")
    var specialty by mutableStateOf("")
    var profileDescription by mutableStateOf("")

    fun init(doctorProfile: DoctorProfile){
        firstName = doctorProfile.firstName
        lastName = doctorProfile.lastName
        address = doctorProfile.address
        city = doctorProfile.city
        postalCode = doctorProfile.postalCode
        specialty = doctorProfile.specialty
        profileDescription = doctorProfile.profileDescription
    }

    fun checkIfEdited(profile: DoctorProfile) = firstName != profile.firstName || lastName != profile.lastName || address != profile.address ||
            city != profile.city || postalCode != profile.postalCode || specialty != profile.specialty || profileDescription != profile.profileDescription
}
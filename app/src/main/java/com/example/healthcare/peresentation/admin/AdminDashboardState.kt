package com.example.healthcare.peresentation.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.healthcare.domain.profile.models.AdminProfile

@Composable
fun rememberAdminDashboardState(
) : AdminDashboardState {
    return remember {
        AdminDashboardState()
    }

}

class AdminDashboardState() {
    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var address by mutableStateOf("")
    var city by mutableStateOf("")
    var postalCode by mutableStateOf("")
    var clinicCity by mutableStateOf("")
    var clinicAddress by mutableStateOf("")
    var clinicName by mutableStateOf("")
    var clinicDescription by mutableStateOf("")

    fun init(adminProfile: AdminProfile){
        firstName = adminProfile.firstName
        lastName = adminProfile.lastName
        address = adminProfile.address
        city = adminProfile.city
        postalCode = adminProfile.postalCode
        clinicName = adminProfile.clinicName
        clinicAddress = adminProfile.clinicAddress
        clinicCity = adminProfile.clinicCity
        clinicDescription = adminProfile.clinicDescription

    }

    fun checkIfEdited(adminProfile: AdminProfile) : Boolean{
        return firstName != adminProfile.firstName || lastName != adminProfile.lastName ||
                address != adminProfile.address || city != adminProfile.city ||
                postalCode != adminProfile.postalCode || clinicCity != adminProfile.clinicCity ||
                clinicAddress != adminProfile.clinicAddress || clinicName != adminProfile.clinicName ||
                adminProfile.clinicDescription != clinicDescription
    }
}
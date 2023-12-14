package com.example.healthcare.domain.profile.models

data class AdminProfile(
    val clinicID: String = "",
    val userID: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val phone: String = "",
    val city: String = "",
    val address: String = "",
    val postalCode: String = "",
    val profile: String = "",
    val role: String = "",
    val createDate: String = "",
    val clinicName: String = "",
    val clinicAddress: String = "",
    val clinicCity: String = "",
    val clinicDescription: String = ""
)
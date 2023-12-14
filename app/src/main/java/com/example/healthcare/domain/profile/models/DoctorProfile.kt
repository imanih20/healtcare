package com.example.healthcare.domain.profile.models

data class DoctorProfile(
    val doctorID: String = "",
    val userID: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val phone: String = "",
    val city: String = "",
    val address: String = "",
    val postalCode: String = "",
    val profile: String = "",
    val role: String = "",
    val createdAt: String = "",
    val specialty: String = "",
    val clinicID: String = "",
    val profileDescription: String = "",
)
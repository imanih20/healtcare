package com.example.healthcare.domain.profile.models

data class PatientProfile(
    val patientID: String = "",
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
    val medicalHistory: String = "",
    val age: Int = 0,
    val height: Double = 0.0,
    val weight: Double = 0.0
)
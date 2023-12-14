package com.example.healthcare.data.profile.service.dto

import com.google.gson.annotations.SerializedName

data class PatientProfileResponse(
    @SerializedName("_id") val patientID: String,
    @SerializedName("userID") val userID: String,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("city") val city: String,
    @SerializedName("address") val address: String,
    @SerializedName("postalCode") val postalCode: String,
    @SerializedName("profile") val profile: String?,
    @SerializedName("role") val role: String,
    @SerializedName("createdAt") val createDate: String,
    @SerializedName("medicalHistory") val medicalHistory: String,
    @SerializedName("age") val age: Int,
    @SerializedName("height") val height: Double,
    @SerializedName("weight") val weight: Double
)
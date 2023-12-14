package com.example.healthcare.data.profile.service.dto

import com.google.gson.annotations.SerializedName

data class PatientProfileRequest(
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("city") val city: String,
    @SerializedName("address") val address: String,
    @SerializedName("postalCode") val postalCode: String,
    @SerializedName("medicalHistory") val medicalHistory: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("age") val age: Int,
    @SerializedName("height") val height: Double,
    @SerializedName("weight") val weight: Double
)
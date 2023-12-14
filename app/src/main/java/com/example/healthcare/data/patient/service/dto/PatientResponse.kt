package com.example.healthcare.data.patient.service.dto

import com.google.gson.annotations.SerializedName

data class PatientResponse (
    @SerializedName("_id") val id: String,
    @SerializedName("medicalHistory") val medicalHistory: String,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("profile") val profile: String,
    @SerializedName("age") val age: Int,
    @SerializedName("weight") val weight: Double,
    @SerializedName("height") val height: Double
)
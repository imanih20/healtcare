package com.example.healthcare.data.patient.service.dto

import com.google.gson.annotations.SerializedName

data class PatientRequest(
    @SerializedName("age") val age: Int,
    @SerializedName("weight") val weight: Double,
    @SerializedName("height") val height: Double,
    @SerializedName("medicalHistory") val medicalHistory: String
)
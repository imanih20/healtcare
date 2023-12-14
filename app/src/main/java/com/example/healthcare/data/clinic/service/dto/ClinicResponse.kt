package com.example.healthcare.data.clinic.service.dto

import com.google.gson.annotations.SerializedName

data class ClinicResponse(
    @SerializedName("_id") val id: String,
    @SerializedName("userID") val adminID: String,
    @SerializedName("clinicName") val clinicName: String,
    @SerializedName("clinicAddress") val clinicAddress: String,
    @SerializedName("clinicCity") val clinicCity: String,
    @SerializedName("clinicDescription") val clinicDescription: String,
)
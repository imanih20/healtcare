package com.example.healthcare.data.clinic.service.dto

import com.google.gson.annotations.SerializedName

data class ClinicRequest(
    @SerializedName("clinicName") val clinicName: String,
    @SerializedName("clinicAddress") val clinicAddress: String,
    @SerializedName("clinicCity") val clinicCity: String,
    @SerializedName("clinicDescription") val clinicDesc: String = "",
)
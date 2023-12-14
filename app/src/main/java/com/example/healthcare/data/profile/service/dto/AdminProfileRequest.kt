package com.example.healthcare.data.profile.service.dto

import com.google.gson.annotations.SerializedName

data class AdminProfileRequest(
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("city") val city: String,
    @SerializedName("address") val address: String,
    @SerializedName("postalCode") val postalCode: String,
    @SerializedName("clinicName") val clinicName: String,
    @SerializedName("clinicAddress") val clinicAddress: String,
    @SerializedName("clinicCity") val clinicCity: String,
    @SerializedName("clinicDescription") val clinicDescription: String
)
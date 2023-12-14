package com.example.healthcare.data.profile.service.dto

import com.google.gson.annotations.SerializedName

data class AdminProfileResponse(
    @SerializedName("_id") val clinicID: String,
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
    @SerializedName("clinicName") val clinicName: String,
    @SerializedName("clinicAddress") val clinicAddress: String,
    @SerializedName("clinicCity") val clinicCity: String,
    @SerializedName("clinicDescription") val clinicDescription: String
)
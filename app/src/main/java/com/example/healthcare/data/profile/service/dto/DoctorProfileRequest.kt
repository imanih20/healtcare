package com.example.healthcare.data.profile.service.dto

import com.google.gson.annotations.SerializedName

data class DoctorProfileRequest(
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("city") val city: String,
    @SerializedName("address") val address: String,
    @SerializedName("postalCode") val postalCode: String,
    @SerializedName("specialty") val specialty: String,
    @SerializedName("clinicID") val clinicID: String,
    @SerializedName("profileDescription") val profileDescription: String,
)
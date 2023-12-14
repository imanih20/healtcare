package com.example.healthcare.data.doctor.service.dto

import com.google.gson.annotations.SerializedName

data class DoctorResponse(
    @SerializedName("_id") val id: String,
    @SerializedName("clinicID") val clinicID: String,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("address") val address: String,
    @SerializedName("city") val city: String,
    @SerializedName("profile") val profileUrl: String,
    @SerializedName("specialty") val specialty: String,
    @SerializedName("degree") val degree: String,
    @SerializedName("profileDescription") val profileDescription: String
)

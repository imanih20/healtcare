package com.example.healthcare.data.doctor.service.dto

import com.google.gson.annotations.SerializedName

data class DoctorRequest(
    @SerializedName("clinicID") val clinicID: String,
    @SerializedName("specialty") val specialty: String,
    @SerializedName("degree") val degree: String,
    @SerializedName("profileDescription") val profileDescription: String
)
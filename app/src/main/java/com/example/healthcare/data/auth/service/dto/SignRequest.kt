package com.example.healthcare.data.auth.service.dto

import com.google.gson.annotations.SerializedName

data class SignRequest(
    @SerializedName("phone") val phone: String,
    @SerializedName("role") val role: String
)
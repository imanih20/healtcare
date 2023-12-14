package com.example.healthcare.data.auth.service.dto

import com.google.gson.annotations.SerializedName

data class VerifyResponse(
    @SerializedName("_id") val userId: String,
    @SerializedName("role") val role: String
)
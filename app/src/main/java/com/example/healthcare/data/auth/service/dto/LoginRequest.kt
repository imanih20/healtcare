package com.example.healthcare.data.auth.service.dto

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("phone") val phone: String
)
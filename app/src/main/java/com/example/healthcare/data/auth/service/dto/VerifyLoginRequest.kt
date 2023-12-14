package com.example.healthcare.data.auth.service.dto

import com.google.gson.annotations.SerializedName

data class VerifyLoginRequest(
    @SerializedName("token") val token: String,
)
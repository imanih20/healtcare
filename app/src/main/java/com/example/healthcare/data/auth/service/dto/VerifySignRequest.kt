package com.example.healthcare.data.auth.service.dto

import com.google.gson.annotations.SerializedName

data class VerifySignRequest(
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("city") val city: String,
    @SerializedName("address") val address: String,
    @SerializedName("postalCode") val postalCode: String,
    @SerializedName("token") val token: String
)
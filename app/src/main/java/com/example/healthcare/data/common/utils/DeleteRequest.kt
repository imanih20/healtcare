package com.example.healthcare.data.common.utils

import com.google.gson.annotations.SerializedName

data class DeleteRequest(
    @SerializedName("id") val id: String
)
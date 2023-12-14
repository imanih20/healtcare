package com.example.healthcare.data.notification.service.dto

import com.google.gson.annotations.SerializedName

data class NotificationResponse(
    @SerializedName("_id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String,
    @SerializedName("isRead") val isRead: Boolean,
    @SerializedName("date") val date: String
)
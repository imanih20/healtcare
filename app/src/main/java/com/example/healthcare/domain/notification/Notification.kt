package com.example.healthcare.domain.notification

data class Notification(
    val id: String,
    val title: String,
    val body: String,
    val isRead: Boolean,
    val date: String
)
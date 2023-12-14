package com.example.healthcare.data.appointment.service.dto

import com.google.gson.annotations.SerializedName

data class AppointmentRequest(
    @SerializedName("patientID") val patientID: String,
    @SerializedName("doctorID") val doctorID: String,
    @SerializedName("dateTime") val date: Long,
    @SerializedName("status") val status: String
)
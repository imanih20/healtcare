package com.example.healthcare.data.prescription.service

import com.google.gson.annotations.SerializedName

data class PrescriptionRequest(
    @SerializedName("patientID") val patientId: String,
    @SerializedName("doctorID") val doctorId: String,
    @SerializedName("medicine") val medicine: String,
    @SerializedName("dosage") val dosage: String,
    @SerializedName("frequency") val frequency: String,
    @SerializedName("startDate") val startDate: String,
    @SerializedName("endDate") val endDate: String,
    @SerializedName("instructions") val instructions: String,
)

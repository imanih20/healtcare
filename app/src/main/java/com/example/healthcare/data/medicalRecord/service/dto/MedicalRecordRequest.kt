package com.example.healthcare.data.medicalRecord.service.dto

import com.google.gson.annotations.SerializedName

data class MedicalRecordRequest(
    @SerializedName("patientID") val patientID: String,
    @SerializedName("recordType") val recordType: String,
    @SerializedName("date") val date: String,
    @SerializedName("notes") val notes: String
)
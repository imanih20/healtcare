package com.example.healthcare.data.medicalRecord.service.dto

import com.google.gson.annotations.SerializedName

data class MedicalRecordResponse(
    @SerializedName("_id") val id: String,
    @SerializedName("patientID") val patientID: String,
    @SerializedName("recordType") val recordType: String,
    @SerializedName("date") val date: String,
    @SerializedName("recordData") val recordData: String,
    @SerializedName("notes") val notes: String
)
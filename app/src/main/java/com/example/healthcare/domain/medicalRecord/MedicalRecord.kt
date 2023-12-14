package com.example.healthcare.domain.medicalRecord

data class MedicalRecord(
    val id: String,
    val patientID: String,
    val recordType: String,
    val date: String,
    val recordData: String,
    val notes: String
)
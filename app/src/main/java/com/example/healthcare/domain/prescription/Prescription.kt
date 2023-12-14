package com.example.healthcare.domain.prescription

import com.example.healthcare.domain.doctor.Doctor
import com.example.healthcare.domain.patient.Patient

data class Prescription(
    val id: String,
    val patient: Patient,
    val doctor: Doctor,
    val medicine: String,
    val dosage: String,
    val frequency: String,
    val startDate: String,
    val endDate: String,
    val instructions: String
)
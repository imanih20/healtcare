package com.example.healthcare.data.prescription.service

import com.example.healthcare.data.doctor.service.dto.DoctorResponse
import com.example.healthcare.data.patient.service.dto.PatientResponse
import com.example.healthcare.domain.doctor.Doctor
import com.example.healthcare.domain.patient.Patient
import com.google.gson.annotations.SerializedName

data class PrescriptionResponse(
    @SerializedName("_id") val id: String,
    @SerializedName("patient") val patient: PatientResponse,
    @SerializedName("doctor") val doctor: DoctorResponse,
    @SerializedName("medicine") val medicine: String,
    @SerializedName("dosage") val dosage: String,
    @SerializedName("frequency") val frequency: String,
    @SerializedName("startDate") val startDate: String,
    @SerializedName("endDate") val endDate: String,
    @SerializedName("instructions") val instructions: String,
)
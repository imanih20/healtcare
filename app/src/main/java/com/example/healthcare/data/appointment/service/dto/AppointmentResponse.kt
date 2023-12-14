package com.example.healthcare.data.appointment.service.dto

import com.example.healthcare.data.doctor.service.dto.DoctorResponse
import com.example.healthcare.data.patient.service.dto.PatientResponse
import com.google.gson.annotations.SerializedName

data class AppointmentResponse(
    @SerializedName("_id") val id: String,
    @SerializedName("patientID") val patient: PatientResponse,
    @SerializedName("doctorID") val doctor: DoctorResponse,
    @SerializedName("dateTime") val date: Long,
    @SerializedName("status") val status: String,
)
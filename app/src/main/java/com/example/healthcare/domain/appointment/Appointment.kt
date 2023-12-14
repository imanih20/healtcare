package com.example.healthcare.domain.appointment

import com.example.healthcare.domain.appointment.util.AppointmentStatus
import com.example.healthcare.domain.doctor.Doctor
import com.example.healthcare.domain.patient.Patient

data class Appointment(
    val id: String,
    val doctor: Doctor,
    val patient: Patient,
    val dateTime: Long,
    val status: AppointmentStatus,
)
package com.example.healthcare.domain.appointment.util

import com.example.healthcare.data.appointment.service.dto.AppointmentResponse
import com.example.healthcare.data.common.utils.ResultMaker
import com.example.healthcare.domain.appointment.Appointment
import com.example.healthcare.domain.doctor.Doctor
import com.example.healthcare.domain.patient.Patient

class AppointmentResult : ResultMaker<Appointment,AppointmentResponse>() {
    override fun getModel(res: AppointmentResponse): Appointment {
        val status = AppointmentStatus.values().firstOrNull { it.value == res.status } ?: AppointmentStatus.UNKNOWN
        return Appointment(
            id = res.id,
            doctor = Doctor.getFromDto(res.doctor),
            patient = Patient.getFromDto(res.patient),
            dateTime = res.date,
            status = status
        )
    }
}
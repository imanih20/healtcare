package com.example.healthcare.domain.appointment.usecase

import com.example.healthcare.data.appointment.service.AppointmentService
import com.example.healthcare.data.appointment.service.dto.AppointmentRequest
import com.example.healthcare.data.appointment.service.dto.AppointmentResponse
import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.domain.appointment.Appointment
import com.example.healthcare.domain.appointment.util.AppointmentResult
import com.example.healthcare.domain.common.BaseResult
import kotlinx.coroutines.flow.Flow

class UpdateAppointmentUseCase(private val service: AppointmentService) {
    suspend operator fun invoke(appointment: Appointment) : Flow<BaseResult<Appointment,WrappedResponse<AppointmentResponse>>>{
        val response = service.updateAppointment(
            appointment.id,
            AppointmentRequest(
                doctorID = appointment.doctor.id,
                patientID = appointment.patient.id,
                date = appointment.dateTime,
                status = appointment.status.value
            )
        )
        return AppointmentResult().getResult(response)

    }
}
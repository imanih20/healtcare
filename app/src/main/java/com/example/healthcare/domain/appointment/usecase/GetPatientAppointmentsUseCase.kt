package com.example.healthcare.domain.appointment.usecase

import com.example.healthcare.data.appointment.service.AppointmentService
import com.example.healthcare.data.appointment.service.dto.AppointmentResponse
import com.example.healthcare.data.common.utils.WrappedListResponse
import com.example.healthcare.domain.appointment.Appointment
import com.example.healthcare.domain.appointment.util.AppointmentResult
import com.example.healthcare.domain.common.BaseResult
import kotlinx.coroutines.flow.Flow

class GetPatientAppointmentsUseCase(private val service: AppointmentService) {
    suspend operator fun invoke(patientID: String) : Flow<BaseResult<List<Appointment>,WrappedListResponse<AppointmentResponse>>> {
        val response = service.getPatientAppointments(patientID)
        return AppointmentResult().getListResult(response)
    }
}
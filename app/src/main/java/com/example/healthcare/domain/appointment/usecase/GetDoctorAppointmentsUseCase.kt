package com.example.healthcare.domain.appointment.usecase

import com.example.healthcare.data.appointment.service.AppointmentService
import com.example.healthcare.data.appointment.service.dto.AppointmentResponse
import com.example.healthcare.data.common.utils.WrappedListResponse
import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.domain.appointment.Appointment
import com.example.healthcare.domain.appointment.util.AppointmentResult
import com.example.healthcare.domain.common.BaseResult
import kotlinx.coroutines.flow.Flow

class GetDoctorAppointmentsUseCase(private val service: AppointmentService) {
    suspend operator fun invoke(doctorID: String) : Flow<BaseResult<List<Appointment>, WrappedListResponse<AppointmentResponse>>> {
        val response = service.getDoctorAppointments(doctorID)
        return AppointmentResult().getListResult(response)
    }
}
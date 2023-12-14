package com.example.healthcare.domain.appointment.usecase

import com.example.healthcare.common.DateUtil
import com.example.healthcare.data.appointment.service.AppointmentService
import com.example.healthcare.data.appointment.service.dto.AppointmentRequest
import com.example.healthcare.data.appointment.service.dto.AppointmentResponse
import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.domain.appointment.Appointment
import com.example.healthcare.domain.appointment.util.AppointmentResult
import com.example.healthcare.domain.common.BaseResult
import kotlinx.coroutines.flow.Flow

class CreateAppointmentUseCase(private val service: AppointmentService) {
    suspend operator fun invoke(
        date: String,
        time: String,
        doctorID: String,
        patientID: String,
        status: String
    ) : Flow<BaseResult<Appointment, WrappedResponse<AppointmentResponse>>> {
        val response = service.createAppointment(
            AppointmentRequest(
                patientID,
                doctorID,
                DateUtil.dateTimeToMil(date,time)?:0,
                status
            )
        )
        return AppointmentResult().getResult(response)
    }
}
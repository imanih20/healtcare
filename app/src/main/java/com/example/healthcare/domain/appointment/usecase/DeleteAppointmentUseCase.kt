package com.example.healthcare.domain.appointment.usecase

import com.example.healthcare.data.appointment.service.AppointmentService
import com.example.healthcare.data.appointment.service.dto.AppointmentResponse
import com.example.healthcare.data.common.utils.DeleteRequest
import com.example.healthcare.data.common.utils.WrappedListResponse
import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.domain.appointment.Appointment
import com.example.healthcare.domain.appointment.util.AppointmentResult
import com.example.healthcare.domain.common.BaseResult
import kotlinx.coroutines.flow.Flow

class DeleteAppointmentUseCase(private val service: AppointmentService) {
    suspend operator fun invoke(idList: List<String>) : Flow<BaseResult<List<Appointment>,WrappedListResponse<AppointmentResponse>>>{
        val response = service.deleteAppointment(idList.map { DeleteRequest(it) })
        return AppointmentResult().getListResult(response)
    }


}
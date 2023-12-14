package com.example.healthcare.domain.clinic.usecase

import com.example.healthcare.data.clinic.service.ClinicService
import com.example.healthcare.data.clinic.service.dto.ClinicResponse
import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.domain.clinic.Clinic
import com.example.healthcare.domain.clinic.ClinicResult
import com.example.healthcare.domain.common.BaseResult
import kotlinx.coroutines.flow.Flow

class GetClinicUseCase(private val service: ClinicService) {
    suspend operator fun invoke(
        adminID: String,
    ) : Flow<BaseResult<Clinic, WrappedResponse<ClinicResponse>>> {
        val response = service.getClinic(adminID)
        return ClinicResult().getResult(response)
    }
}
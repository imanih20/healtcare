package com.example.healthcare.domain.clinic.usecase

import com.example.healthcare.data.clinic.service.ClinicService
import com.example.healthcare.data.clinic.service.dto.ClinicResponse
import com.example.healthcare.data.common.utils.WrappedListResponse
import com.example.healthcare.domain.clinic.Clinic
import com.example.healthcare.domain.clinic.ClinicResult
import com.example.healthcare.domain.common.BaseResult
import kotlinx.coroutines.flow.Flow

class GetAllClinicsUseCase(private val service: ClinicService) {
    suspend operator fun invoke() : Flow<BaseResult<List<Clinic>,WrappedListResponse<ClinicResponse>>> {
        val response = service.getAllClinics()
        return ClinicResult().getListResult(response)
    }
}
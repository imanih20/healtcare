package com.example.healthcare.domain.clinic.usecase

import com.example.healthcare.data.clinic.service.ClinicService
import com.example.healthcare.data.clinic.service.dto.ClinicRequest
import com.example.healthcare.data.clinic.service.dto.ClinicResponse
import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.domain.clinic.Clinic
import com.example.healthcare.domain.clinic.ClinicResult
import com.example.healthcare.domain.common.BaseResult
import kotlinx.coroutines.flow.Flow

class UpdateClinicUseCase(private val service: ClinicService) {
    suspend operator fun invoke(
        clinic: Clinic
    ) : Flow<BaseResult<Clinic,WrappedResponse<ClinicResponse>>> {
        val response = service.updateClinic(clinic.id, ClinicRequest(
            clinicName = clinic.name,
            clinicAddress = clinic.address,
            clinicCity = clinic.city,
            clinicDesc = clinic.description
        ))
        return ClinicResult().getResult(response)
    }
}
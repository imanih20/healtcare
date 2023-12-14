package com.example.healthcare.domain.clinic.usecase

import com.example.healthcare.data.clinic.service.ClinicService
import com.example.healthcare.data.clinic.service.dto.ClinicRequest
import com.example.healthcare.data.clinic.service.dto.ClinicResponse
import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.domain.clinic.Clinic
import com.example.healthcare.domain.clinic.ClinicResult
import com.example.healthcare.domain.common.BaseResult
import kotlinx.coroutines.flow.Flow

class CreateClinicUseCase(private val service: ClinicService) {
    suspend operator fun invoke(
        clninicName: String,
        clinicAddress: String,
        clinicCity: String,
        clinicDesc: String = "",
    ) : Flow<BaseResult<Clinic,WrappedResponse<ClinicResponse>>> {
        val response = service.createClinic(
            ClinicRequest(
                clninicName,
                clinicAddress,
                clinicCity,
                clinicDesc
            )
        )
        return ClinicResult().getResult(response)
    }
}
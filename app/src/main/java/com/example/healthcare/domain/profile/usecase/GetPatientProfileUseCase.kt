package com.example.healthcare.domain.profile.usecase

import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.data.profile.service.ProfileService
import com.example.healthcare.data.profile.service.dto.PatientProfileResponse
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.profile.models.PatientProfile
import com.example.healthcare.domain.profile.utils.PatientProfileResult
import kotlinx.coroutines.flow.Flow

class GetPatientProfileUseCase(private val service: ProfileService) {
    suspend operator fun invoke() : Flow<BaseResult<PatientProfile,WrappedResponse<PatientProfileResponse>>> {
        val response = service.getPatientProfile()
        return PatientProfileResult().getResult(response)
    }
}
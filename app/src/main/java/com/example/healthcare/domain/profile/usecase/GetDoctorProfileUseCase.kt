package com.example.healthcare.domain.profile.usecase

import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.data.profile.service.ProfileService
import com.example.healthcare.data.profile.service.dto.DoctorProfileResponse
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.profile.models.DoctorProfile
import com.example.healthcare.domain.profile.utils.DoctorProfileResult
import kotlinx.coroutines.flow.Flow

class GetDoctorProfileUseCase(private val service: ProfileService) {
    suspend operator fun invoke() : Flow<BaseResult<DoctorProfile,WrappedResponse<DoctorProfileResponse>>> {
        val response = service.getDoctorProfile()
        return DoctorProfileResult().getResult(response)
    }
}
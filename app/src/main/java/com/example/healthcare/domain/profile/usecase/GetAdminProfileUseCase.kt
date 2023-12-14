package com.example.healthcare.domain.profile.usecase

import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.data.profile.service.ProfileService
import com.example.healthcare.data.profile.service.dto.AdminProfileResponse
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.profile.models.AdminProfile
import com.example.healthcare.domain.profile.utils.AdminProfileResult
import kotlinx.coroutines.flow.Flow

class GetAdminProfileUseCase(private val service: ProfileService) {
    suspend operator fun invoke() : Flow<BaseResult<AdminProfile,WrappedResponse<AdminProfileResponse>>> {
        val response = service.getAdminProfile()
        return AdminProfileResult().getResult(response)
    }
}
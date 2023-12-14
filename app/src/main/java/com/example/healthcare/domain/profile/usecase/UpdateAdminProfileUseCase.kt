package com.example.healthcare.domain.profile.usecase

import com.example.healthcare.data.common.utils.WrappedListResponse
import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.data.profile.service.ProfileService
import com.example.healthcare.data.profile.service.dto.AdminProfileRequest
import com.example.healthcare.data.profile.service.dto.AdminProfileResponse
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.profile.models.AdminProfile
import com.example.healthcare.domain.profile.utils.AdminProfileResult
import kotlinx.coroutines.flow.Flow

class UpdateAdminProfileUseCase(
    private val profileService: ProfileService
) {
    suspend operator fun invoke(adminProfile: AdminProfile) : Flow<BaseResult<AdminProfile,WrappedResponse<AdminProfileResponse>>> {
        val response = profileService.updateAdminProfile(
            adminProfile.userID, AdminProfileRequest(
                firstName = adminProfile.firstName,
                lastName = adminProfile.lastName,
                city = adminProfile.city,
                address = adminProfile.address,
                postalCode = adminProfile.postalCode,
                clinicName = adminProfile.clinicName,
                clinicAddress = adminProfile.clinicAddress,
                clinicDescription = adminProfile.clinicDescription,
                clinicCity = adminProfile.clinicCity
            )
        )
        return AdminProfileResult().getResult(response)
    }
}
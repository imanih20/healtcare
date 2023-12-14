package com.example.healthcare.domain.profile.usecase

import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.data.profile.service.ProfileService
import com.example.healthcare.data.profile.service.dto.DoctorProfileRequest
import com.example.healthcare.data.profile.service.dto.DoctorProfileResponse
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.profile.models.DoctorProfile
import com.example.healthcare.domain.profile.utils.DoctorProfileResult
import kotlinx.coroutines.flow.Flow

class UpdateDoctorProfileUseCase(private val service: ProfileService) {
    suspend operator fun invoke(
        doctorProfile: DoctorProfile
    ) : Flow<BaseResult<DoctorProfile,WrappedResponse<DoctorProfileResponse>>> {
        val response = service.updateDoctorProfile(
            doctorProfile.userID, DoctorProfileRequest(
                firstName = doctorProfile.firstName,
                lastName = doctorProfile.lastName,
                phone = doctorProfile.phone,
                city = doctorProfile.city,
                address = doctorProfile.address,
                postalCode = doctorProfile.postalCode,
                specialty = doctorProfile.specialty,
                clinicID = doctorProfile.clinicID,
                profileDescription = doctorProfile.profileDescription
            )
        )
        return DoctorProfileResult().getResult(response)
    }
}
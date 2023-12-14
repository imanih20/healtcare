package com.example.healthcare.domain.profile.usecase

import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.data.profile.service.ProfileService
import com.example.healthcare.data.profile.service.dto.DoctorProfileRequest
import com.example.healthcare.data.profile.service.dto.DoctorProfileResponse
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.profile.models.DoctorProfile
import com.example.healthcare.domain.profile.utils.DoctorProfileResult
import kotlinx.coroutines.flow.Flow

class CreateDoctorProfileUseCase(private val service: ProfileService) {
    suspend operator fun invoke(
        firstName: String = "",
        lastName: String = "",
        phone: String = "",
        city: String = "",
        address: String = "",
        postalCode: String = "",
        specialty: String = "",
        clinicID: String,
        profileDescription: String = "",
    ) : Flow<BaseResult<DoctorProfile,WrappedResponse<DoctorProfileResponse>>> {
        val response = service.createDoctorProfile(
            DoctorProfileRequest(
                firstName, lastName, phone, city, address, postalCode, specialty, clinicID, profileDescription
            )
        )
        return DoctorProfileResult().getResult(response)
    }
}
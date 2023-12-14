package com.example.healthcare.domain.profile.usecase

import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.data.profile.service.ProfileService
import com.example.healthcare.data.profile.service.dto.PatientProfileRequest
import com.example.healthcare.data.profile.service.dto.PatientProfileResponse
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.profile.models.PatientProfile
import com.example.healthcare.domain.profile.utils.PatientProfileResult
import kotlinx.coroutines.flow.Flow

class UpdatePatientProfileUseCase(private val service: ProfileService) {
    suspend operator fun invoke(
        patientProfile: PatientProfile
    ) : Flow<BaseResult<PatientProfile,WrappedResponse<PatientProfileResponse>>> {
        val response = service.updatePatientProfile(
            patientProfile.userID,PatientProfileRequest(
                firstName = patientProfile.firstName,
                lastName = patientProfile.lastName,
                address = patientProfile.address,
                postalCode = patientProfile.postalCode,
                city = patientProfile.city,
                phone = patientProfile.phone,
                age = patientProfile.age,
                height = patientProfile.height,
                weight = patientProfile.height,
                medicalHistory = patientProfile.medicalHistory
            )
        )
        return PatientProfileResult().getResult(response)
    }
}
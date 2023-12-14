package com.example.healthcare.domain.profile.usecase

import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.data.profile.service.ProfileService
import com.example.healthcare.data.profile.service.dto.PatientProfileRequest
import com.example.healthcare.data.profile.service.dto.PatientProfileResponse
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.profile.models.PatientProfile
import com.example.healthcare.domain.profile.utils.PatientProfileResult
import kotlinx.coroutines.flow.Flow

class CreatePatientProfileUseCase(private val service: ProfileService) {
    suspend operator fun invoke(
        firstName: String = "",
        lastName: String = "",
        phone: String = "",
        city: String = "",
        address: String = "",
        postalCode: String = "",
        medicalHistory: String = "",
        age: Int = 0,
        height: Double = 0.0,
        weight: Double = 0.0
    ) : Flow<BaseResult<PatientProfile,WrappedResponse<PatientProfileResponse>>>{
        val response = service.createPatientProfile(
            PatientProfileRequest(
                firstName, lastName, city, address, postalCode, medicalHistory, phone, age, height, weight
            )
        )
        return PatientProfileResult().getResult(response)
    }
}
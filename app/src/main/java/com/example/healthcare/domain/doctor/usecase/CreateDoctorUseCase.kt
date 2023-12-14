package com.example.healthcare.domain.doctor.usecase

import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.data.doctor.service.DoctorService
import com.example.healthcare.data.doctor.service.dto.DoctorRequest
import com.example.healthcare.data.doctor.service.dto.DoctorResponse
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.doctor.Doctor
import com.example.healthcare.domain.doctor.DoctorResult
import kotlinx.coroutines.flow.Flow

class CreateDoctorUseCase(private val service: DoctorService) {
    suspend operator fun invoke(
        clinicID: String,
        specialty: String,
        degree: String,
        profileDescription: String
    ) : Flow<BaseResult<Doctor,WrappedResponse<DoctorResponse>>> {
        val response = service.createDoctor(DoctorRequest(
            clinicID = clinicID,
            specialty = specialty,
            degree = degree,
            profileDescription = profileDescription
        ))
        return DoctorResult().getResult(response)
    }
}
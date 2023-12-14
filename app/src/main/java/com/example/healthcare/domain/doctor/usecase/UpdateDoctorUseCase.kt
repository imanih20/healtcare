package com.example.healthcare.domain.doctor.usecase

import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.data.doctor.service.DoctorService
import com.example.healthcare.data.doctor.service.dto.DoctorRequest
import com.example.healthcare.data.doctor.service.dto.DoctorResponse
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.doctor.Doctor
import com.example.healthcare.domain.doctor.DoctorResult
import kotlinx.coroutines.flow.Flow

class UpdateDoctorUseCase(private val service: DoctorService) {
    suspend operator fun invoke(doctor: Doctor) : Flow<BaseResult<Doctor,WrappedResponse<DoctorResponse>>> {
        val response = service.updateDoctor(doctor.id, DoctorRequest(
            clinicID = doctor.clinicID,
            specialty = doctor.specialty,
            degree = doctor.degree,
            profileDescription = doctor.profileDescription
        ))
        return DoctorResult().getResult(response)
    }
}
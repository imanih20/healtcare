package com.example.healthcare.domain.doctor.usecase

import com.example.healthcare.data.common.utils.WrappedListResponse
import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.data.doctor.service.DoctorService
import com.example.healthcare.data.doctor.service.dto.DoctorResponse
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.doctor.Doctor
import com.example.healthcare.domain.doctor.DoctorResult
import kotlinx.coroutines.flow.Flow

class GetClinicDoctorsUseCase(private val service: DoctorService) {
    suspend operator fun invoke(
        clinicID: String
    ) : Flow<BaseResult<List<Doctor>, WrappedListResponse<DoctorResponse>>> {
        val response = service.getDoctors(clinicID)
        return DoctorResult().getListResult(response)
    }
}
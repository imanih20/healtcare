package com.example.healthcare.domain.patient.usecase

import com.example.healthcare.data.common.utils.WrappedListResponse
import com.example.healthcare.data.patient.service.PatientService
import com.example.healthcare.data.patient.service.dto.PatientResponse
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.patient.Patient
import com.example.healthcare.domain.patient.PatientResult
import kotlinx.coroutines.flow.Flow

class GetClinicPatientsUseCase(private val service: PatientService) {
    suspend operator fun invoke(clinicID: String) : Flow<BaseResult<List<Patient>, WrappedListResponse<PatientResponse>>>{
        val response = service.getPatients()
        return PatientResult().getListResult(response)
    }
}
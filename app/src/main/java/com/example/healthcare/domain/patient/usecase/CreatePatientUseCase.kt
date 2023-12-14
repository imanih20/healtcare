package com.example.healthcare.domain.patient.usecase

import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.data.patient.service.PatientService
import com.example.healthcare.data.patient.service.dto.PatientRequest
import com.example.healthcare.data.patient.service.dto.PatientResponse
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.patient.Patient
import com.example.healthcare.domain.patient.PatientResult
import kotlinx.coroutines.flow.Flow

class CreatePatientUseCase(private val service: PatientService) {
    suspend operator fun invoke(
        age: Int,
        medicalHistory: String,
        height: Double,
        weight: Double
    ) : Flow<BaseResult<Patient,WrappedResponse<PatientResponse>>> {
        val response = service.createPatient(
            PatientRequest(
                age = age,
                medicalHistory = medicalHistory,
                height = height,
                weight = weight
            )
        )
        return PatientResult().getResult(response)
    }
}
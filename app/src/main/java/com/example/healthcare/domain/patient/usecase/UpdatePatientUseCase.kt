package com.example.healthcare.domain.patient.usecase

import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.data.patient.service.PatientService
import com.example.healthcare.data.patient.service.dto.PatientRequest
import com.example.healthcare.data.patient.service.dto.PatientResponse
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.patient.Patient
import com.example.healthcare.domain.patient.PatientResult
import kotlinx.coroutines.flow.Flow

class UpdatePatientUseCase(private val service: PatientService) {
    suspend operator fun invoke(patient: Patient) : Flow<BaseResult<Patient,WrappedResponse<PatientResponse>>> {
        val response = service.updatePatient(patient.id, PatientRequest(
            age = patient.age,
            medicalHistory = patient.medicalHistory,
            weight = patient.weight,
            height = patient.height
        ))
        return PatientResult().getResult(response)
    }
}
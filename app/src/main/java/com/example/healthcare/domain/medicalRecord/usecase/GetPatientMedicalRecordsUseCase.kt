package com.example.healthcare.domain.medicalRecord.usecase

import com.example.healthcare.data.common.utils.WrappedListResponse
import com.example.healthcare.data.medicalRecord.service.MedicalRecordService
import com.example.healthcare.data.medicalRecord.service.dto.MedicalRecordResponse
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.medicalRecord.MedicalRecord
import com.example.healthcare.domain.medicalRecord.MedicalRecordResult
import kotlinx.coroutines.flow.Flow

data class GetPatientMedicalRecordsUseCase(private val service: MedicalRecordService) {
    suspend operator fun invoke(patientId: String) : Flow<BaseResult<List<MedicalRecord>,WrappedListResponse<MedicalRecordResponse>>> {
        val response = service.getPatientMedicalRecords(patientId)
        return MedicalRecordResult().getListResult(response)
    }
}

package com.example.healthcare.domain.medicalRecord.usecase

import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.data.medicalRecord.service.MedicalRecordService
import com.example.healthcare.data.medicalRecord.service.dto.MedicalRecordResponse
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.medicalRecord.MedicalRecord
import com.example.healthcare.domain.medicalRecord.MedicalRecordResult
import kotlinx.coroutines.flow.Flow

class GetMedicalRecordUseCase(private val service: MedicalRecordService) {
    suspend operator fun invoke(id: String) : Flow<BaseResult<MedicalRecord,WrappedResponse<MedicalRecordResponse>>> {
        val response = service.getMedicalRecord(id)
        return MedicalRecordResult().getResult(response)
    }
}
package com.example.healthcare.domain.medicalRecord.usecase

import com.example.healthcare.data.common.utils.DeleteRequest
import com.example.healthcare.data.common.utils.WrappedListResponse
import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.data.medicalRecord.service.MedicalRecordService
import com.example.healthcare.data.medicalRecord.service.dto.MedicalRecordResponse
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.medicalRecord.MedicalRecord
import com.example.healthcare.domain.medicalRecord.MedicalRecordResult
import kotlinx.coroutines.flow.Flow

class DeleteMedicalRecordUseCase(private val service: MedicalRecordService) {
    suspend operator fun invoke(idList: List<String>) : Flow<BaseResult<List<MedicalRecord>,WrappedListResponse<MedicalRecordResponse>>> {
        val response = service.deleteMedicalRecord(idList.map { DeleteRequest(it) })
        return MedicalRecordResult().getListResult(response)
    }
}
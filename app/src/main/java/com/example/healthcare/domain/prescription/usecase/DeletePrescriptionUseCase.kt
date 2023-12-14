package com.example.healthcare.domain.prescription.usecase

import com.example.healthcare.data.common.utils.DeleteRequest
import com.example.healthcare.data.common.utils.WrappedListResponse
import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.data.prescription.service.PrescriptionResponse
import com.example.healthcare.data.prescription.service.PrescriptionService
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.prescription.Prescription
import com.example.healthcare.domain.prescription.utils.PrescriptionResult
import kotlinx.coroutines.flow.Flow

class DeletePrescriptionUseCase(private val service: PrescriptionService) {
    suspend operator fun invoke(prescriptionIdList: List<String>) : Flow<BaseResult<List<Prescription>,WrappedListResponse<PrescriptionResponse>>>{
        val response = service.deletePrescription(prescriptionIdList.map { DeleteRequest(it) })
        return PrescriptionResult().getListResult(response)
    }
}
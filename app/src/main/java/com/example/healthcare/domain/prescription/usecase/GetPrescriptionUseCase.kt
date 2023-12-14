package com.example.healthcare.domain.prescription.usecase

import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.data.prescription.service.PrescriptionResponse
import com.example.healthcare.data.prescription.service.PrescriptionService
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.prescription.Prescription
import com.example.healthcare.domain.prescription.utils.PrescriptionResult
import kotlinx.coroutines.flow.Flow

class GetPrescriptionUseCase(private val service: PrescriptionService) {
    suspend operator fun invoke(prescriptionId: String) : Flow<BaseResult<Prescription,WrappedResponse<PrescriptionResponse>>> {
        val response = service.getPrescription(prescriptionId)
        return PrescriptionResult().getResult(response)
    }
}
package com.example.healthcare.domain.prescription.usecase

import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.data.prescription.service.PrescriptionRequest
import com.example.healthcare.data.prescription.service.PrescriptionResponse
import com.example.healthcare.data.prescription.service.PrescriptionService
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.prescription.Prescription
import com.example.healthcare.domain.prescription.utils.PrescriptionResult
import kotlinx.coroutines.flow.Flow

class UpdatePrescriptionUseCase(private val service: PrescriptionService) {
    suspend operator fun invoke(prescription: Prescription) : Flow<BaseResult<Prescription,WrappedResponse<PrescriptionResponse>>> {
        val response = service.updatePrescription(prescription.id,PrescriptionRequest(
            patientId = prescription.patient.id,
            doctorId = prescription.doctor.id,
            medicine = prescription.medicine,
            dosage = prescription.dosage,
            frequency = prescription.frequency,
            startDate = prescription.startDate,
            endDate = prescription.endDate,
            instructions = prescription.instructions,
        ))
        return PrescriptionResult().getResult(response)
    }
}
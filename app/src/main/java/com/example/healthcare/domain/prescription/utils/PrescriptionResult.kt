package com.example.healthcare.domain.prescription.utils

import com.example.healthcare.data.common.utils.ResultMaker
import com.example.healthcare.data.prescription.service.PrescriptionResponse
import com.example.healthcare.data.prescription.service.PrescriptionService
import com.example.healthcare.domain.doctor.Doctor
import com.example.healthcare.domain.patient.Patient
import com.example.healthcare.domain.prescription.Prescription

class PrescriptionResult : ResultMaker<Prescription, PrescriptionResponse>() {
    override fun getModel(res: PrescriptionResponse): Prescription {
        return Prescription(
            id = res.id,
            patient = Patient.getFromDto(res.patient),
            doctor = Doctor.getFromDto(res.doctor),
            medicine = res.medicine,
            dosage = res.dosage,
            frequency = res.frequency,
            startDate = res.startDate,
            endDate = res.endDate,
            instructions = res.instructions
        )
    }
}
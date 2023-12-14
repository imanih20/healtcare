package com.example.healthcare.domain.medicalRecord

import com.example.healthcare.data.common.utils.ResultMaker
import com.example.healthcare.data.medicalRecord.service.dto.MedicalRecordResponse

class MedicalRecordResult : ResultMaker<MedicalRecord,MedicalRecordResponse>() {
    override fun getModel(res: MedicalRecordResponse): MedicalRecord {
        return MedicalRecord(
            res.id,
            res.patientID,
            res.recordType,
            res.date,
            res.recordData,
            res.notes
        )
    }
}
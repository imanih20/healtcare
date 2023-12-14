package com.example.healthcare.domain.patient

import com.example.healthcare.data.common.utils.ResultMaker
import com.example.healthcare.data.patient.service.dto.PatientResponse

class PatientResult : ResultMaker<Patient,PatientResponse>() {
    override fun getModel(res: PatientResponse): Patient {
        return Patient.getFromDto(res)
    }
}
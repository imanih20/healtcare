package com.example.healthcare.domain.doctor

import com.example.healthcare.data.common.utils.ResultMaker
import com.example.healthcare.data.doctor.service.dto.DoctorResponse

class DoctorResult : ResultMaker<Doctor,DoctorResponse>() {
    override fun getModel(res: DoctorResponse): Doctor {
        return Doctor.getFromDto(res)
    }
}
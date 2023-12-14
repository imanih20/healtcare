package com.example.healthcare.domain.clinic

import com.example.healthcare.data.clinic.service.dto.ClinicResponse
import com.example.healthcare.data.common.utils.ResultMaker

class ClinicResult : ResultMaker<Clinic,ClinicResponse>() {
    override fun getModel(res: ClinicResponse): Clinic {
        return Clinic.getFromResponse(res)
    }
}
package com.example.healthcare.domain.profile.utils

import com.example.healthcare.data.common.utils.ResultMaker
import com.example.healthcare.data.profile.service.dto.PatientProfileResponse
import com.example.healthcare.domain.profile.models.PatientProfile

class PatientProfileResult : ResultMaker<PatientProfile,PatientProfileResponse>() {
    override fun getModel(res: PatientProfileResponse): PatientProfile {
        return PatientProfile(
            patientID = res.patientID,
            userID = res.userID,
            firstName = res.firstName,
            lastName = res.lastName,
            phone = res.phone,
            city = res.city,
            address = res.address,
            postalCode = res.postalCode,
            profile = res.profile?:"",
            role = res.role,
            createdAt = res.createDate,
            age = res.age,
            height = res.height,
            weight = res.weight,
            medicalHistory = res.medicalHistory
        )
    }
}
package com.example.healthcare.domain.profile.utils

import com.example.healthcare.data.common.utils.ResultMaker
import com.example.healthcare.data.profile.service.dto.DoctorProfileResponse
import com.example.healthcare.domain.profile.models.DoctorProfile

class DoctorProfileResult : ResultMaker<DoctorProfile,DoctorProfileResponse>() {
    override fun getModel(res: DoctorProfileResponse): DoctorProfile {
        return DoctorProfile(
            doctorID = res.doctorID,
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
            specialty = res.specialty,
            clinicID = res.clinicID,
            profileDescription = res.profileDescription
        )
    }
}
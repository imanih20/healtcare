package com.example.healthcare.domain.profile.utils

import com.example.healthcare.data.common.utils.ResultMaker
import com.example.healthcare.data.profile.service.dto.AdminProfileResponse
import com.example.healthcare.domain.profile.models.AdminProfile

class AdminProfileResult : ResultMaker<AdminProfile,AdminProfileResponse>() {
    override fun getModel(res: AdminProfileResponse): AdminProfile {
        return AdminProfile(
            clinicID = res.clinicID,
            userID = res.userID,
            firstName = res.firstName,
            lastName = res.lastName,
            phone = res.phone,
            city = res.city,
            address = res.address,
            postalCode = res.postalCode,
            profile = res.profile?:"",
            role = res.role,
            createDate = res.createDate,
            clinicName = res.clinicName,
            clinicAddress = res.clinicAddress,
            clinicCity = res.clinicCity,
            clinicDescription = res.clinicDescription
        )
    }

}
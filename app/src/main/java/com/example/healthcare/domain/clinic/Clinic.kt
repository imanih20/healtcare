package com.example.healthcare.domain.clinic

import com.example.healthcare.data.clinic.service.dto.ClinicResponse

data class Clinic(
    val id: String,
    val adminId: String,
    val name: String,
    val address: String,
    val city: String,
    val description: String
){
    companion object{
        fun getFromResponse(response: ClinicResponse) = Clinic(
            response.id,
            response.adminID,
            response.clinicName,
            response.clinicAddress,
            response.clinicCity,
            response.clinicDescription
        )
    }
}
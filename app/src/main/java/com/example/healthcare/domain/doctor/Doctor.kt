package com.example.healthcare.domain.doctor

import com.example.healthcare.data.doctor.service.dto.DoctorResponse

data class Doctor(
    val id: String,
    val clinicID: String,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val address: String,
    val profileUrl: String,
    val city: String,
    val specialty: String,
    val degree: String,
    val profileDescription: String
){
    companion object{
        fun getFromDto(doctorResponse: DoctorResponse) : Doctor {
            return Doctor(
                id = doctorResponse.id,
                clinicID = doctorResponse.clinicID,
                firstName = doctorResponse.firstName,
                lastName = doctorResponse.lastName,
                profileUrl = doctorResponse.profileUrl,
                address = doctorResponse.address,
                phone = doctorResponse.phone,
                city = doctorResponse.city,
                specialty = doctorResponse.specialty,
                degree = doctorResponse.degree,
                profileDescription = doctorResponse.profileDescription
            )
        }
    }
}
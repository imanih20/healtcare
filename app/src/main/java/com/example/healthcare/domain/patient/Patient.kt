package com.example.healthcare.domain.patient

import com.example.healthcare.data.patient.service.dto.PatientResponse

data class Patient(
    val id: String,
    val medicalHistory: String,
    val firstName: String,
    val lastName: String,
    val profile: String,
    val age: Int,
    val weight: Double,
    val height: Double
){
    companion object {
        fun getFromDto(res: PatientResponse) : Patient{
            return Patient(
                id = res.id,
                medicalHistory = res.medicalHistory,
                firstName = res.firstName,
                lastName = res.lastName,
                profile = res.profile,
                age = res.age,
                weight = res.weight,
                height = res.height
            )
        }
    }
}
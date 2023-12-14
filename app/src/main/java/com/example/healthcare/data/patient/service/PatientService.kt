package com.example.healthcare.data.patient.service

import com.example.healthcare.data.patient.service.dto.PatientRequest
import com.example.healthcare.data.patient.service.dto.PatientResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PatientService {
    @POST("patient/")
    suspend fun createPatient(@Body patientRequest: PatientRequest): Response<PatientResponse>

    @PUT("patient/{id}")
    suspend fun updatePatient(@Path("id") id: String, @Body patientRequest: PatientRequest): Response<PatientResponse>

    @GET("patient/")
    suspend fun getPatients(): Response<List<PatientResponse>>
}
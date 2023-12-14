package com.example.healthcare.data.clinic.service

import com.example.healthcare.data.clinic.service.dto.ClinicRequest
import com.example.healthcare.data.clinic.service.dto.ClinicResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ClinicService {
    @POST("clinic/")
    suspend fun createClinic(@Body clinic: ClinicRequest): Response<ClinicResponse>

    @GET("clinic/admin/")
    suspend fun getClinic(@Query("adminID") adminID: String): Response<ClinicResponse>

    @GET("clinic/")
    suspend fun getAllClinics(): Response<List<ClinicResponse>>

    @PUT("clinic/{id}")
    suspend fun updateClinic(@Path("id") id: String, @Body clinic: ClinicRequest): Response<ClinicResponse>

}
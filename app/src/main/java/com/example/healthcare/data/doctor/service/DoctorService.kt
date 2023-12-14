package com.example.healthcare.data.doctor.service

import com.example.healthcare.data.doctor.service.dto.DoctorRequest
import com.example.healthcare.data.doctor.service.dto.DoctorResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface DoctorService {
    @POST("doctor/")
    suspend fun createDoctor(@Body doctorRequest: DoctorRequest) : Response<DoctorResponse>

    @GET("doctor/")
    suspend fun getDoctors(@Query("clinicID") clinicID: String) : Response<List<DoctorResponse>>

    @PUT("doctor/{id}")
    suspend fun updateDoctor(@Path("id") id: String, @Body doctorRequest: DoctorRequest) : Response<DoctorResponse>
}
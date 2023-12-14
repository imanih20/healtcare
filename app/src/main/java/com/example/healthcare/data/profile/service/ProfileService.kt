package com.example.healthcare.data.profile.service

import com.example.healthcare.data.profile.service.dto.AdminProfileRequest
import com.example.healthcare.data.profile.service.dto.AdminProfileResponse
import com.example.healthcare.data.profile.service.dto.DoctorProfileRequest
import com.example.healthcare.data.profile.service.dto.DoctorProfileResponse
import com.example.healthcare.data.profile.service.dto.PatientProfileRequest
import com.example.healthcare.data.profile.service.dto.PatientProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ProfileService {
    @GET("profile/doctor/")
    suspend fun getDoctorProfile(): Response<DoctorProfileResponse>

    @GET("profile/admin/")
    suspend fun getAdminProfile(): Response<AdminProfileResponse>

    @GET("profile/patient/")
    suspend fun getPatientProfile(): Response<PatientProfileResponse>

    @PUT("profile/admin/{id}")
    suspend fun updateAdminProfile(
        @Path("id") id: String,
        @Body adminProfileRequest: AdminProfileRequest
    ) : Response<AdminProfileResponse>

    @PUT("profile/doctor/{id}")
    suspend fun updateDoctorProfile(
        @Path("id") id: String,
        @Body doctorProfileRequest: DoctorProfileRequest
    ) : Response<DoctorProfileResponse>

    @PUT("profile/patient/{id}")
    suspend fun updatePatientProfile(
        @Path("id") id: String,
        @Body patientProfileRequest: PatientProfileRequest
    ) : Response<PatientProfileResponse>

    @POST("profile/patient")
    suspend fun createPatientProfile(@Body profileRequest: PatientProfileRequest) : Response<PatientProfileResponse>

    @POST("profile/doctor")
    suspend fun createDoctorProfile(@Body doctorProfileRequest: DoctorProfileRequest) : Response<DoctorProfileResponse>
}
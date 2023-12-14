package com.example.healthcare.data.medicalRecord.service

import com.example.healthcare.data.common.utils.DeleteRequest
import com.example.healthcare.data.medicalRecord.service.dto.MedicalRecordRequest
import com.example.healthcare.data.medicalRecord.service.dto.MedicalRecordResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface MedicalRecordService {
    @Multipart
    @POST("medicalRecord/")
    suspend fun createMedicalRecord(
        @Part file: MultipartBody.Part,
        @Body medicalRecordRequest: MedicalRecordRequest
    ): Response<MedicalRecordResponse>

    @GET("medicalRecord/")
    suspend fun getPatientMedicalRecords(@Query("patientID") patientId: String): Response<List<MedicalRecordResponse>>

    @GET("medicalRecord/{id}")
    suspend fun getMedicalRecord(@Path("id") id: String): Response<MedicalRecordResponse>

    @PUT("medicalRecord/{id}")
    suspend fun updateMedicalRecord(@Path("id") id: String, @Body medicalRecordRequest: MedicalRecordRequest): Response<MedicalRecordResponse>

    @DELETE("medicalRecord/")
    suspend fun deleteMedicalRecord(@Body deleteRequest: List<DeleteRequest>): Response<List<MedicalRecordResponse>>
}
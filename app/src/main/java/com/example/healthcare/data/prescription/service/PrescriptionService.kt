package com.example.healthcare.data.prescription.service

import com.example.healthcare.data.common.utils.DeleteRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PrescriptionService {
    @GET("prescription/")
    suspend fun getPrescriptions(@Query("patientID") patientId: String): Response<List<PrescriptionResponse>>

    @POST("prescription/")
    suspend fun createPrescription(@Body prescriptionRequest: List<PrescriptionRequest>) : Response<List<PrescriptionResponse>>

    @GET("prescription/{id}")
    suspend fun getPrescription(@Path("id") id: String) : Response<PrescriptionResponse>

    @PUT("prescription/{id}")
    suspend fun updatePrescription(@Path("id") id: String, @Body prescriptionRequest: PrescriptionRequest) : Response<PrescriptionResponse>

    @DELETE("prescription/{id}")
    suspend fun deletePrescription(@Body deleteRequest: List<DeleteRequest>) : Response<List<PrescriptionResponse>>
}
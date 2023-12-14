package com.example.healthcare.data.appointment.service

import com.example.healthcare.data.appointment.service.dto.AppointmentRequest
import com.example.healthcare.data.appointment.service.dto.AppointmentResponse
import com.example.healthcare.data.common.utils.DeleteRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface AppointmentService {
    @POST("appointment/")
    suspend fun createAppointment(@Body appointmentRequest: AppointmentRequest) : Response<AppointmentResponse>

    @GET("appointment/doctor")
    suspend fun getDoctorAppointments(
        @Query("doctorID") doctorID: String,
        @Query("status") status: String = ""
    ) : Response<List<AppointmentResponse>>

    @GET("appointment/patient")
    suspend fun getPatientAppointments(
        @Query("patientID") patientID: String,
        @Query("status") status: String = ""
    ): Response<List<AppointmentResponse>>

    @PUT("appointment/{id}")
    suspend fun updateAppointment(@Path("id") id: String, @Body appointmentRequest: AppointmentRequest) : Response<AppointmentResponse>

    @DELETE("appointment/")
    suspend fun deleteAppointment(@Body deleteRequest: List<DeleteRequest>) : Response<List<AppointmentResponse>>

}
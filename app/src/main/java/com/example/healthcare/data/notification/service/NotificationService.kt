package com.example.healthcare.data.notification.service

import com.example.healthcare.data.common.utils.DeleteRequest
import com.example.healthcare.data.notification.service.dto.NotificationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface NotificationService {
    @GET("notification/")
    suspend fun getNotifications(): Response<List<NotificationResponse>>

    @DELETE("notification/")
    suspend fun deleteNotification(@Body delete: List<DeleteRequest>): Response<List<NotificationResponse>>

    @PUT("notification/")
    suspend fun readNotifications(): Response<List<NotificationResponse>>

    @GET("notification/unread/")
    suspend fun getUnreadNotifications() : Response<List<NotificationResponse>>
}
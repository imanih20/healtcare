package com.example.healthcare.domain.notification.usecase

import com.example.healthcare.data.common.utils.WrappedListResponse
import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.data.notification.service.NotificationService
import com.example.healthcare.data.notification.service.dto.NotificationResponse
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.notification.Notification
import com.example.healthcare.domain.notification.utils.NotificationResult
import kotlinx.coroutines.flow.Flow

class UpdateIsReadUseCase(private val service: NotificationService) {
    suspend operator fun invoke(
    ) : Flow<BaseResult<List<Notification>,WrappedListResponse<NotificationResponse>>>{
        val response = service.readNotifications()
        return NotificationResult().getListResult(response)
    }
}
package com.example.healthcare.domain.notification.usecase

import com.example.healthcare.data.common.utils.DeleteRequest
import com.example.healthcare.data.common.utils.WrappedListResponse
import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.data.notification.service.NotificationService
import com.example.healthcare.data.notification.service.dto.NotificationResponse
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.notification.Notification
import com.example.healthcare.domain.notification.utils.NotificationResult
import kotlinx.coroutines.flow.Flow

class DeleteNotificationUseCase(private val service: NotificationService) {
    suspend operator fun invoke(notificationIdList: List<String>) : Flow<BaseResult<List<Notification>,WrappedListResponse<NotificationResponse>>>{
        val response = service.deleteNotification(notificationIdList.map { DeleteRequest(it) })
        return NotificationResult().getListResult(response)
    }
}
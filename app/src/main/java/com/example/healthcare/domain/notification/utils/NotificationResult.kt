package com.example.healthcare.domain.notification.utils

import com.example.healthcare.data.common.utils.ResultMaker
import com.example.healthcare.data.notification.service.dto.NotificationResponse
import com.example.healthcare.domain.notification.Notification

class NotificationResult : ResultMaker<Notification,NotificationResponse>() {
    override fun getModel(res: NotificationResponse): Notification {
        return Notification(
            id = res.id,
            title = res.title,
            body = res.body,
            isRead = res.isRead,
            date = res.date
        )
    }
}
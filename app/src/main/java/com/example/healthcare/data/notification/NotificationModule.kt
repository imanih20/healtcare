package com.example.healthcare.data.notification

import com.example.healthcare.data.common.modules.networkModule
import com.example.healthcare.data.notification.service.NotificationService
import com.example.healthcare.domain.notification.usecase.DeleteNotificationUseCase
import com.example.healthcare.domain.notification.usecase.GetNotificationsUseCase
import com.example.healthcare.domain.notification.usecase.GetUnreadNotificationsUseCase
import com.example.healthcare.domain.notification.usecase.UpdateIsReadUseCase
import org.koin.dsl.module
import retrofit2.Retrofit

val notificationModule = module {
    includes(networkModule)

    single {
        val retrofit : Retrofit = get()
        retrofit.create(NotificationService::class.java)
    }

    single {
        GetNotificationsUseCase(get())
    }

    single {
        UpdateIsReadUseCase(get())
    }

    single {
        DeleteNotificationUseCase(get())
    }

    single {
        GetUnreadNotificationsUseCase(get())
    }
}
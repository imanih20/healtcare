package com.example.healthcare.peresentation.notification

import com.example.healthcare.data.notification.service.NotificationService
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.notification.Notification
import com.example.healthcare.domain.notification.usecase.DeleteNotificationUseCase
import com.example.healthcare.domain.notification.usecase.GetNotificationsUseCase
import com.example.healthcare.domain.notification.usecase.UpdateIsReadUseCase
import com.example.healthcare.peresentation.common.utils.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotificationViewModel(
    private val getNotifications: GetNotificationsUseCase,
    private val updateIsRead: UpdateIsReadUseCase,
    private val deleteNotifications: DeleteNotificationUseCase
): BaseViewModel() {
    private val _notifications = MutableStateFlow<ArrayList<Notification>>(arrayListOf())
    val notifications = _notifications.asStateFlow()

    fun getAllNotifications(){
        checkListRequest(
            request = {
                getNotifications()
            }
        ){
            when(it){
                is BaseResult.Success -> {
                    _notifications.value = it.data as ArrayList<Notification>
                }
                is BaseResult.Error -> {

                }
            }
        }
    }

    fun readNotifications() {
        checkListRequest(
            request = {
                updateIsRead()
            }
        ){
            when(it){
                is BaseResult.Success -> {
                    getAllNotifications()
                }
                is BaseResult.Error -> {

                }
            }
        }
    }

    fun deleteNotification(idList: List<String>){
        checkListRequest(
            request = {
                deleteNotifications(idList)
            }
        ){
            when(it){
                is BaseResult.Success -> {
                    getAllNotifications()
                }
                is BaseResult.Error -> {

                }
            }
        }
    }
}
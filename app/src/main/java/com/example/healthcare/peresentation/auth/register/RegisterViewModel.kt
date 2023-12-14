package com.example.healthcare.peresentation.auth.register

import com.example.healthcare.common.Prefs
import com.example.healthcare.domain.auth.usecase.RegisterUseCase
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.peresentation.common.utils.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel(
    private val register: RegisterUseCase,
    private val prefs: Prefs
) : BaseViewModel() {
    private val _registerRequestState = MutableStateFlow<RequestState>(RequestState.Init)
    val registerRequestState = _registerRequestState.asStateFlow()

    fun registerUser(phone: String, role: String) {
        setLoading()
        checkRequest(
            request = {
                register(role, phone)
            }
        ) {
            hideLoading()
            when(it){
                is BaseResult.Success -> {
                    prefs.writeToken(it.data as String)
                    _registerRequestState.value = RequestState.Success
                }
                is BaseResult.Error -> {
                    _registerRequestState.value = RequestState.Error(it.rawResponse.message)
                }
            }
        }
    }
}
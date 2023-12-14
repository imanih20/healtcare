package com.example.healthcare.peresentation.auth.login

import android.util.Log
import com.example.healthcare.common.Prefs
import com.example.healthcare.domain.auth.usecase.LoginUseCase
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.peresentation.common.utils.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(
    private val login: LoginUseCase,
    private val prefs: Prefs
) : BaseViewModel(){

    private val _loginRequestState = MutableStateFlow<RequestState>(RequestState.Init)
    val loginRequestState = _loginRequestState.asStateFlow()

    fun loginUser(phone: String){
        setLoading()
        checkRequest(
            request = {
                Log.i("login_phone",phone)
                login(phone)
            }
        ){
            hideLoading()
            when(it){
                is BaseResult.Success -> {
                    prefs.writeToken(it.data as String)
                    _loginRequestState.value = RequestState.Success
                }
                is BaseResult.Error -> {
                    Log.e("phone",it.rawResponse.code.toString())
                    _loginRequestState.value = RequestState.Error(it.rawResponse.message)
                }
            }
        }
    }

}
package com.example.healthcare.peresentation.auth.verify

import com.example.healthcare.common.Prefs
import com.example.healthcare.domain.auth.Verify
import com.example.healthcare.domain.auth.usecase.VerifyLoginUseCase
import com.example.healthcare.domain.auth.usecase.VerifyRegisterUseCase
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.peresentation.common.utils.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class VerifyViewModel(
    private val verifyRegister: VerifyRegisterUseCase,
    private val verifyLogin: VerifyLoginUseCase,
    private val prefs: Prefs
) : BaseViewModel(){
    private val _verifyRegisterState = MutableStateFlow<RequestState>(RequestState.Init)
    val verifyRegisterState = _verifyRegisterState.asStateFlow()


    private val _verifyLoginState = MutableStateFlow<RequestState>(RequestState.Init)
    val verifyLoginState = _verifyLoginState.asStateFlow()

    fun verifyUserRegister(
        firstName: String,
        lastName: String,
        city: String,
        address: String,
        postalCode: String,
        token: String
    ){
        setLoading()
        checkRequest(request = {
            verifyRegister(firstName,lastName,city,address,postalCode,token)
        }){
            hideLoading()
            when(it){
                is BaseResult.Success -> {
                    val verify = it.data as Verify
                    prefs.writeIsLogIn(true)
                    prefs.writeUserId(verify.userId)
                    prefs.writeRole(verify.role)
                    _verifyRegisterState.value = RequestState.Success
                }
                is BaseResult.Error -> {
                    _verifyRegisterState.value = RequestState.Error(it.rawResponse.message)
                }
            }
        }
    }

    fun verifyUserLogin(token: String){
        setLoading()
        checkRequest(request = {
            verifyLogin(token)
        }){
            hideLoading()
            when(it){
                is BaseResult.Success -> {
                    val verify = it.data as Verify
                    prefs.writeIsLogIn(true)
                    prefs.writeUserId(verify.userId)
                    prefs.writeRole(verify.role)
                    _verifyLoginState.value = RequestState.Success
                }
                is BaseResult.Error -> {
                    _verifyLoginState.value = RequestState.Error(it.rawResponse.message)
                }
            }
        }
    }
}
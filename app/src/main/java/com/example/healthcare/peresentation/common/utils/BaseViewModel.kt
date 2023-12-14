package com.example.healthcare.peresentation.common.utils

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthcare.common.NoNetworkException
import com.example.healthcare.data.common.utils.WrappedListResponse
import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.domain.common.BaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading get() = _isLoading.asStateFlow()

    private val _isShowInternetDialog = MutableStateFlow(false)
    val isShowInternetDialog get() = _isShowInternetDialog.asStateFlow()

    protected fun setLoading(){
        _isLoading.value = true
    }
    protected fun hideLoading(){
        _isLoading.value = false
    }

    fun showInternetDialog() {
        _isShowInternetDialog.value = true
    }

    fun hideInternetDialog() {
        _isShowInternetDialog.value = false
    }

    fun checkRequest(request:suspend ()-> Flow<BaseResult<*, WrappedResponse<*>>>, collect: FlowCollector<BaseResult<*, WrappedResponse<*>>>){
        viewModelScope.launch {
            try {
                request()
                    .catch {
                        hideLoading()
                        if (it.cause == NoNetworkException()){
                            showInternetDialog()
                        }
                        Log.e("test_flow_catch", it.cause?.stackTrace.contentToString())
                    }
                    .collect(collect)
            }catch (e:NoNetworkException){
                showInternetDialog()
                hideLoading()
            }catch (e:Exception){
                hideLoading()
                //make toast or show snackbar
                Log.e("test_catch",e.stackTrace.contentToString())
            }

        }
    }
    fun checkListRequest(request:suspend ()->Flow<BaseResult<*, WrappedListResponse<*>>>, collect:FlowCollector<BaseResult<*,WrappedListResponse<*>>>){
        viewModelScope.launch {
            try {
                request()
                    .catch {
                        hideLoading()
                        if (it.cause == NoNetworkException()){
                            Log.e("test_flow_catch", it.cause?.stackTrace.contentToString())
                            showInternetDialog()
                        }
                    }
                    .collect(collect)
            }catch (e:NoNetworkException){
                hideLoading()
                showInternetDialog()
            }catch (e: Exception) {
                hideLoading()
                Log.e("test_catch",e.stackTrace.contentToString())
            }

        }
    }


    sealed class RequestState {
        object Init: RequestState()
        object Success: RequestState()
        class Error(val message: String) : RequestState()
    }

}
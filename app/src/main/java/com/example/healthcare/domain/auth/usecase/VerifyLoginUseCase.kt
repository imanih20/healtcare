package com.example.healthcare.domain.auth.usecase

import com.example.healthcare.data.auth.service.AuthService
import com.example.healthcare.data.auth.service.dto.VerifyLoginRequest
import com.example.healthcare.data.auth.service.dto.VerifyResponse
import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.domain.auth.Verify
import com.example.healthcare.domain.auth.utils.VerifyResult
import com.example.healthcare.domain.common.BaseResult
import kotlinx.coroutines.flow.Flow

class VerifyLoginUseCase(private val service: AuthService) {
    suspend operator  fun invoke(smsToken: String) : Flow<BaseResult<Verify,WrappedResponse<VerifyResponse>>>{
        val response = service.verifyLogin(VerifyLoginRequest(smsToken))
        return VerifyResult().getResult(response)
    }
}
package com.example.healthcare.domain.auth.usecase

import com.example.healthcare.data.auth.service.AuthService
import com.example.healthcare.data.auth.service.dto.AuthResponse
import com.example.healthcare.data.auth.service.dto.SignRequest
import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.domain.auth.utils.AuthResult
import com.example.healthcare.domain.common.BaseResult
import kotlinx.coroutines.flow.Flow

class RegisterUseCase(private val service: AuthService) {
    suspend operator fun invoke(
        role: String,
        phone: String
    ) : Flow<BaseResult<String,WrappedResponse<AuthResponse>>> {
        val response = service.register(SignRequest(phone,role))
        return AuthResult().getResult(response)
    }
}
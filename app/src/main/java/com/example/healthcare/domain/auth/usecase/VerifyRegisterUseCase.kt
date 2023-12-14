package com.example.healthcare.domain.auth.usecase

import com.example.healthcare.data.auth.service.AuthService
import com.example.healthcare.data.auth.service.dto.VerifyResponse
import com.example.healthcare.data.auth.service.dto.VerifySignRequest
import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.domain.auth.Verify
import com.example.healthcare.domain.auth.utils.VerifyResult
import com.example.healthcare.domain.common.BaseResult
import kotlinx.coroutines.flow.Flow

class VerifyRegisterUseCase(private val service: AuthService) {
    suspend operator fun invoke(
        firstName: String,
        lastName: String,
        city: String,
        address: String,
        postalCode: String,
        token: String
    ) : Flow<BaseResult<Verify,WrappedResponse<VerifyResponse>>> {
        val response = service.verifySignUp(
            VerifySignRequest(
                firstName,
                lastName,
                city,
                address,
                postalCode,
                token
            )
        )
        return VerifyResult().getResult(response)
    }
}
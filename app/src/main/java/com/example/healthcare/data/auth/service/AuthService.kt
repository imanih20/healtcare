package com.example.healthcare.data.auth.service

import com.example.healthcare.data.auth.service.dto.AuthResponse
import com.example.healthcare.data.auth.service.dto.LoginRequest
import com.example.healthcare.data.auth.service.dto.SignRequest
import com.example.healthcare.data.auth.service.dto.VerifyLoginRequest
import com.example.healthcare.data.auth.service.dto.VerifyResponse
import com.example.healthcare.data.auth.service.dto.VerifySignRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest) : Response<AuthResponse>

    @POST("auth/signUp")
    suspend fun register(@Body signRequest: SignRequest) : Response<AuthResponse>

    @POST("auth/verifyLogin")
    suspend fun verifyLogin(@Body verifyLoginRequest: VerifyLoginRequest) : Response<VerifyResponse>

    @POST("auth/verifySignUp")
    suspend fun verifySignUp(@Body verifySignRequest: VerifySignRequest) : Response<VerifyResponse>
}
package com.example.healthcare.domain.auth.utils

import com.example.healthcare.data.auth.service.dto.AuthResponse
import com.example.healthcare.data.common.utils.ResultMaker

class AuthResult : ResultMaker<String,AuthResponse>() {
    override fun getModel(res: AuthResponse): String {
        return res.token
    }
}
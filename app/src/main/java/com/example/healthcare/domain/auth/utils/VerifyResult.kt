package com.example.healthcare.domain.auth.utils

import com.example.healthcare.data.auth.service.dto.VerifyResponse
import com.example.healthcare.data.common.utils.ResultMaker
import com.example.healthcare.domain.auth.Verify

class VerifyResult : ResultMaker<Verify,VerifyResponse>(){
    override fun getModel(res: VerifyResponse): Verify {
        return Verify(res.userId,res.role)
    }
}
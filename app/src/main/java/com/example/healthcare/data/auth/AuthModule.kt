package com.example.healthcare.data.auth

import com.example.healthcare.data.auth.service.AuthService
import com.example.healthcare.data.common.modules.networkModule
import com.example.healthcare.data.common.modules.prefsModule
import com.example.healthcare.domain.auth.usecase.LoginUseCase
import com.example.healthcare.domain.auth.usecase.RegisterUseCase
import com.example.healthcare.domain.auth.usecase.VerifyLoginUseCase
import com.example.healthcare.domain.auth.usecase.VerifyRegisterUseCase
import com.example.healthcare.peresentation.auth.login.LoginViewModel
import com.example.healthcare.peresentation.auth.register.RegisterViewModel
import com.example.healthcare.peresentation.auth.verify.VerifyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val authModule = module {
    includes(networkModule, prefsModule)

    single {
        val retrofit : Retrofit = get()
        retrofit.create(AuthService::class.java)
    }


    single {
        RegisterUseCase(get())
    }
    single {
        LoginUseCase(get())
    }

    single {
        VerifyLoginUseCase(get())
    }

    single{
        VerifyRegisterUseCase(get())
    }

}
package com.example.healthcare.data.clinic

import com.example.healthcare.data.clinic.service.ClinicService
import com.example.healthcare.data.common.modules.networkModule
import com.example.healthcare.domain.clinic.usecase.CreateClinicUseCase
import com.example.healthcare.domain.clinic.usecase.GetAllClinicsUseCase
import com.example.healthcare.domain.clinic.usecase.GetClinicUseCase
import com.example.healthcare.domain.clinic.usecase.UpdateClinicUseCase
import org.koin.dsl.module
import retrofit2.Retrofit

val clinicModule = module {
    includes(networkModule)

    single {
        val retrofit = get<Retrofit>()
        retrofit.create(ClinicService::class.java)
    }

    single{
        CreateClinicUseCase(get())
    }

    single {
        GetAllClinicsUseCase(get())
    }

    single {
        UpdateClinicUseCase(get())
    }

    single {
        GetClinicUseCase(get())
    }

}
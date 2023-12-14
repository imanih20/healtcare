package com.example.healthcare.data.doctor

import com.example.healthcare.data.common.modules.networkModule
import com.example.healthcare.data.doctor.service.dto.DoctorResponse
import com.example.healthcare.data.doctor.service.DoctorService
import com.example.healthcare.domain.doctor.usecase.CreateDoctorUseCase
import com.example.healthcare.domain.doctor.usecase.GetClinicDoctorsUseCase
import com.example.healthcare.domain.doctor.usecase.UpdateDoctorUseCase
import org.koin.dsl.module
import retrofit2.Retrofit

val doctorModule = module {
    includes(networkModule)

    single {
        val retrofit: Retrofit = get()
        retrofit.create(DoctorService::class.java)
    }

    single {
        CreateDoctorUseCase(get())
    }

    single {
        GetClinicDoctorsUseCase(get())
    }

    single {
        UpdateDoctorUseCase(get())
    }
}



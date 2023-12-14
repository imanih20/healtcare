package com.example.healthcare.data.patient

import com.example.healthcare.data.common.modules.networkModule
import com.example.healthcare.data.patient.service.PatientService
import com.example.healthcare.domain.patient.usecase.CreatePatientUseCase
import com.example.healthcare.domain.patient.usecase.GetClinicPatientsUseCase
import com.example.healthcare.domain.patient.usecase.UpdatePatientUseCase
import org.koin.dsl.module
import retrofit2.Retrofit

val patientModule = module {
    includes(networkModule)

    single {
        val retrofit: Retrofit = get()
        retrofit.create(PatientService::class.java)
    }

    single {
        CreatePatientUseCase(get())
    }

    single {
        UpdatePatientUseCase(get())
    }

    single {
        GetClinicPatientsUseCase(get())
    }
}
package com.example.healthcare.data.prescription

import com.example.healthcare.data.common.modules.networkModule
import com.example.healthcare.data.prescription.service.PrescriptionService
import com.example.healthcare.domain.prescription.usecase.CreatePrescriptionUseCase
import com.example.healthcare.domain.prescription.usecase.DeletePrescriptionUseCase
import com.example.healthcare.domain.prescription.usecase.GetPatientPrescriptionsUseCase
import com.example.healthcare.domain.prescription.usecase.GetPrescriptionUseCase
import com.example.healthcare.domain.prescription.usecase.UpdatePrescriptionUseCase
import org.koin.dsl.module
import retrofit2.Retrofit

val prescriptionModule = module {
    includes(networkModule)

    single {
        val retrofit : Retrofit = get()
        retrofit.create(PrescriptionService::class.java)
    }

    single {
        CreatePrescriptionUseCase(get())
    }

    single {
        GetPatientPrescriptionsUseCase(get())
    }

    single {
        GetPrescriptionUseCase(get())
    }

    single {
        DeletePrescriptionUseCase(get())
    }

    single {
        UpdatePrescriptionUseCase(get())
    }
}
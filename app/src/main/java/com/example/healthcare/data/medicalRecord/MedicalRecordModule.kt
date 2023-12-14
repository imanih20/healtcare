package com.example.healthcare.data.medicalRecord

import com.example.healthcare.data.common.modules.networkModule
import com.example.healthcare.data.medicalRecord.service.MedicalRecordService
import com.example.healthcare.domain.medicalRecord.usecase.CreateMedicalRecordUseCase
import com.example.healthcare.domain.medicalRecord.usecase.DeleteMedicalRecordUseCase
import com.example.healthcare.domain.medicalRecord.usecase.GetMedicalRecordUseCase
import com.example.healthcare.domain.medicalRecord.usecase.GetPatientMedicalRecordsUseCase
import com.example.healthcare.domain.medicalRecord.usecase.UpdateMedicalRecordUseCase
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.http.DELETE

val medicalRecordModule = module {
    includes(networkModule)

    single{
        val retrofit: Retrofit = get()
        retrofit.create(MedicalRecordService::class.java)
    }

    single {
        CreateMedicalRecordUseCase(get())
    }

    single {
        DeleteMedicalRecordUseCase(get())
    }

    single {
        GetMedicalRecordUseCase(get())
    }

    single {
        GetPatientMedicalRecordsUseCase(get())
    }

    single {
        UpdateMedicalRecordUseCase(get())
    }
}
package com.example.healthcare.data.profile

import com.example.healthcare.data.clinic.clinicModule
import com.example.healthcare.data.common.modules.networkModule
import com.example.healthcare.data.common.modules.prefsModule
import com.example.healthcare.data.doctor.doctorModule
import com.example.healthcare.data.notification.notificationModule
import com.example.healthcare.data.patient.patientModule
import com.example.healthcare.data.profile.service.ProfileService
import com.example.healthcare.domain.profile.usecase.CreateDoctorProfileUseCase
import com.example.healthcare.domain.profile.usecase.CreatePatientProfileUseCase
import com.example.healthcare.domain.profile.usecase.GetAdminProfileUseCase
import com.example.healthcare.domain.profile.usecase.GetDoctorProfileUseCase
import com.example.healthcare.domain.profile.usecase.GetPatientProfileUseCase
import com.example.healthcare.domain.profile.usecase.UpdateAdminProfileUseCase
import com.example.healthcare.domain.profile.usecase.UpdateDoctorProfileUseCase
import com.example.healthcare.domain.profile.usecase.UpdatePatientProfileUseCase
import com.example.healthcare.peresentation.profile.CreateProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val profileModule = module {
    includes(networkModule)

    single {
        val retrofit = get<Retrofit>()
        retrofit.create(ProfileService::class.java)
    }

    single {
        GetAdminProfileUseCase(get())
    }

    single {
        GetDoctorProfileUseCase(get())
    }

    single {
        GetPatientProfileUseCase(get())
    }

    single {
        UpdateAdminProfileUseCase(get())
    }
    single {
        UpdateDoctorProfileUseCase(get())
    }
    single { UpdatePatientProfileUseCase(get()) }

    single {
        CreateDoctorProfileUseCase(get())
    }

    single {
        CreatePatientProfileUseCase(get())
    }
}
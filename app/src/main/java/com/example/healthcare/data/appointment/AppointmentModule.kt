package com.example.healthcare.data.appointment

import com.example.healthcare.data.appointment.service.AppointmentService
import com.example.healthcare.data.common.modules.networkModule
import com.example.healthcare.domain.appointment.usecase.CreateAppointmentUseCase
import com.example.healthcare.domain.appointment.usecase.DeleteAppointmentUseCase
import com.example.healthcare.domain.appointment.usecase.GetDoctorAppointmentsUseCase
import com.example.healthcare.domain.appointment.usecase.GetPatientAppointmentsUseCase
import com.example.healthcare.domain.appointment.usecase.UpdateAppointmentUseCase
import org.koin.dsl.module
import retrofit2.Retrofit


val appointmentModule = module {
    includes(networkModule)

    single {
        val retrofit : Retrofit = get()
        retrofit.create(AppointmentService::class.java)
    }

    single {
        CreateAppointmentUseCase(get())
    }

    single {
        GetDoctorAppointmentsUseCase(get())
    }

    single {
        GetPatientAppointmentsUseCase(get())
    }

    single {
        UpdateAppointmentUseCase(get())
    }

    single {
        DeleteAppointmentUseCase(get())
    }

}
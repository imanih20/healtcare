package com.example.healthcare

import android.app.Application
import com.example.healthcare.data.appointment.appointmentModule
import com.example.healthcare.data.auth.authModule
import com.example.healthcare.data.clinic.clinicModule
import com.example.healthcare.data.common.modules.mainModule
import com.example.healthcare.data.common.modules.networkModule
import com.example.healthcare.data.common.modules.prefsModule
import com.example.healthcare.data.doctor.doctorModule
import com.example.healthcare.data.medicalRecord.medicalRecordModule
import com.example.healthcare.data.patient.patientModule
import com.example.healthcare.data.profile.profileModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(mainModule)
        }
    }
}
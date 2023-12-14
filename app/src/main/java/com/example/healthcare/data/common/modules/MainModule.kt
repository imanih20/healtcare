package com.example.healthcare.data.common.modules

import com.example.healthcare.data.appointment.appointmentModule
import com.example.healthcare.data.auth.authModule
import com.example.healthcare.data.clinic.clinicModule
import com.example.healthcare.data.doctor.doctorModule
import com.example.healthcare.data.medicalRecord.medicalRecordModule
import com.example.healthcare.data.notification.notificationModule
import com.example.healthcare.data.patient.patientModule
import com.example.healthcare.data.prescription.prescriptionModule
import com.example.healthcare.data.profile.profileModule
import com.example.healthcare.peresentation.admin.AdminDashboardViewModel
import com.example.healthcare.peresentation.appointment.viewmodels.AppointmentListViewModel
import com.example.healthcare.peresentation.auth.login.LoginViewModel
import com.example.healthcare.peresentation.auth.register.RegisterViewModel
import com.example.healthcare.peresentation.auth.verify.VerifyViewModel
import com.example.healthcare.peresentation.doctor.viewModels.DoctorDashboardViewModel
import com.example.healthcare.peresentation.doctor.viewModels.DoctorListViewModel
import com.example.healthcare.peresentation.medicalRecord.MedicalRecordViewModel
import com.example.healthcare.peresentation.notification.NotificationViewModel
import com.example.healthcare.peresentation.patient.PatientDashboardViewModel
import com.example.healthcare.peresentation.patient.PatientListViewModel
import com.example.healthcare.peresentation.prescription.PrescriptionListViewModel
import com.example.healthcare.peresentation.profile.CreateProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    includes(
        appointmentModule,
        clinicModule,
        doctorModule,
        medicalRecordModule,
        notificationModule,
        patientModule,
        prescriptionModule,
        profileModule,
        authModule,
        prefsModule
    )

    //login
    viewModel {
        LoginViewModel(
            login = get(),
            prefs = get()
        )
    }

    //register
    viewModel {
        RegisterViewModel(
            register = get(),
            prefs = get()
        )
    }

    //verify
    viewModel {
        VerifyViewModel(
            verifyRegister = get(),
            verifyLogin = get(),
            prefs = get()
        )
    }

    //admin dashboard viewmodel
    viewModel {
        AdminDashboardViewModel(
            getAdminProf = get(),
            getClinicPatients = get(),
            getClinicDoctors =  get(),
            updateAdminProfileUseCase = get(),
            getUnreadNotifs = get()
        )
    }

    //appointment list
    viewModel {
        AppointmentListViewModel(
            updateAppointment = get(),
            getDoctorApps = get(),
            getPatientApps = get(),
            id = it.get(),
            role = it.get()
        )
    }

    //doctor dashboard
    viewModel {
        DoctorDashboardViewModel(
            getDoctorProfile = get(),
            getDoctorAppointments = get(),
            getUnreadNotifications = get(),
            updateAppointment = get(),
            updateDoctorProfile = get()
        )
    }

    //doctor list
    viewModel {
        DoctorListViewModel(
            getClinicDoctors = get(),
            clinicId = it.get()
        )
    }

    //medical list
    viewModel {
        MedicalRecordViewModel(
            getPatientMedicalRecords = get(),
            createMedicalRecord = get(),
            deleteMedicalRecord = get(),
            patientId = it.get()
        )
    }

    //notifications
    viewModel {
        NotificationViewModel(
            getNotifications = get(),
            updateIsRead = get(),
            deleteNotifications = get()
        )
    }

    //patient dashboard
    viewModel {
        PatientDashboardViewModel(
            getPatientProfile = get(),
            getPatientPrescriptions = get(),
            getPatientAppointments = get(),
            getPatientMedicalRecords = get(),
            getUnreadNotifications = get(),
            updateAppointment = get(),
            updatePatientProfile = get()
        )
    }

    //patient list
    viewModel{
        PatientListViewModel(
            getDoctorAppointments = get(),
            getClinicPatientsUseCase = get(),
            id = it.get(),
            role = it.get()
        )
    }

    //prescription list
    viewModel {
        PrescriptionListViewModel(
            getPatientPrescriptions = get(),
            patientId = it.get()
        )
    }

    //create profile
    viewModel {
        CreateProfileViewModel(
            createDoctor = get(),
            createPatient = get(),
            createClinic = get(),
            getAllClinics = get()
        )
    }
}
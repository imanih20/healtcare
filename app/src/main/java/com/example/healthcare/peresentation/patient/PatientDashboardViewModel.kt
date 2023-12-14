package com.example.healthcare.peresentation.patient

import com.example.healthcare.domain.appointment.Appointment
import com.example.healthcare.domain.appointment.usecase.GetPatientAppointmentsUseCase
import com.example.healthcare.domain.appointment.usecase.UpdateAppointmentUseCase
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.medicalRecord.MedicalRecord
import com.example.healthcare.domain.medicalRecord.usecase.GetPatientMedicalRecordsUseCase
import com.example.healthcare.domain.notification.usecase.GetUnreadNotificationsUseCase
import com.example.healthcare.domain.prescription.Prescription
import com.example.healthcare.domain.prescription.usecase.GetPatientPrescriptionsUseCase
import com.example.healthcare.domain.profile.models.DoctorProfile
import com.example.healthcare.domain.profile.models.PatientProfile
import com.example.healthcare.domain.profile.usecase.GetPatientProfileUseCase
import com.example.healthcare.domain.profile.usecase.UpdatePatientProfileUseCase
import com.example.healthcare.peresentation.common.utils.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PatientDashboardViewModel(
    private val getPatientProfile: GetPatientProfileUseCase,
    private val getPatientPrescriptions: GetPatientPrescriptionsUseCase,
    private val getPatientAppointments: GetPatientAppointmentsUseCase,
    private val getPatientMedicalRecords: GetPatientMedicalRecordsUseCase,
    private val getUnreadNotifications: GetUnreadNotificationsUseCase,
    private val updateAppointment: UpdateAppointmentUseCase,
    private val updatePatientProfile: UpdatePatientProfileUseCase
) : BaseViewModel() {
    private val _profile = MutableStateFlow<PatientProfile>(PatientProfile())
    val profile get() = _profile.asStateFlow()

    private val _prescriptions = MutableStateFlow<List<Prescription>>(emptyList())
    val prescription get() = _prescriptions.asStateFlow()

    private val _appointments = MutableStateFlow<List<Appointment>>(emptyList())
    val appointments get() = _appointments.asStateFlow()

    private val _records = MutableStateFlow<List<MedicalRecord>>(emptyList())
    val records get() = _records.asStateFlow()

    private val _notifCount = MutableStateFlow(0)
    val notifCount get() = _notifCount.asStateFlow()

    init {
        countNotifications()
        getProfile()
    }

    private fun getProfile() = checkRequest(
        request = {
            setLoading()
            getPatientProfile()
        }
    ){
        hideLoading()
        when(it){
            is BaseResult.Success -> {
                val patientProfile = it.data as PatientProfile
                _profile.value = patientProfile
                getAppointments(patientProfile.patientID)
                getPrescriptions(patientProfile.patientID)
                getMedicalRecords(patientProfile.patientID)
            }
            is BaseResult.Error -> {

            }
        }
    }

    private fun countNotifications() = checkListRequest(
        request = {
            getUnreadNotifications()
        }
    ){
        when(it){
            is BaseResult.Success -> {
                _notifCount.value = (it.data as List<*>).size
            }
            is BaseResult.Error -> {

            }
        }
    }

    private fun getPrescriptions(patientId: String) = checkListRequest(
        request = {
            getPatientPrescriptions(patientId)
        }
    ){
        when(it){
            is BaseResult.Success -> {
                _prescriptions.value = it.data as List<Prescription>
            }
            is BaseResult.Error -> {

            }
        }
    }

    private fun getAppointments(patientId: String) = checkListRequest(
        request = {
            getPatientAppointments(patientId)
        }
    ){
        when(it){
            is BaseResult.Success -> {
                _appointments.value = it.data as List<Appointment>
            }
            is BaseResult.Error -> {

            }
        }
    }

    private fun getMedicalRecords(patientId: String) = checkListRequest(
        request = {
            getPatientMedicalRecords(patientId)
        }
    ){
        when(it){
            is BaseResult.Success -> {
                _records.value = it.data as List<MedicalRecord>
            }
            is BaseResult.Error -> {

            }
        }
    }

    fun updateAppointmentStatus(appointment: Appointment) = checkRequest(
        request = {
            updateAppointment(appointment)
        }
    ){
        when(it){
            is BaseResult.Success -> {}
            is BaseResult.Error -> {}
        }
    }

    fun updateProfile(patientProfile: PatientProfile) = checkRequest(
        request = {
            updatePatientProfile(patientProfile)
        }
    ){
        when(it){
            is BaseResult.Success->{
                _profile.value = it.data as PatientProfile
            }
            is BaseResult.Error -> {

            }
        }
    }
}
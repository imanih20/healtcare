package com.example.healthcare.peresentation.doctor.viewModels

import androidx.compose.runtime.collectAsState
import com.example.healthcare.domain.appointment.Appointment
import com.example.healthcare.domain.appointment.usecase.GetDoctorAppointmentsUseCase
import com.example.healthcare.domain.appointment.usecase.UpdateAppointmentUseCase
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.notification.usecase.GetUnreadNotificationsUseCase
import com.example.healthcare.domain.patient.Patient
import com.example.healthcare.domain.profile.models.DoctorProfile
import com.example.healthcare.domain.profile.usecase.GetDoctorProfileUseCase
import com.example.healthcare.domain.profile.usecase.UpdateDoctorProfileUseCase
import com.example.healthcare.peresentation.common.utils.BaseViewModel
import com.example.healthcare.peresentation.common.utils.DoctorAppointmentActions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DoctorDashboardViewModel(
    private val getDoctorProfile: GetDoctorProfileUseCase,
    private val getUnreadNotifications: GetUnreadNotificationsUseCase,
    private val getDoctorAppointments: GetDoctorAppointmentsUseCase,
    private val updateAppointment: UpdateAppointmentUseCase,
    private val updateDoctorProfile : UpdateDoctorProfileUseCase
) : BaseViewModel() {
    private val _profile = MutableStateFlow(DoctorProfile())
    val profile get() = _profile.asStateFlow()

    init {
        getProfile()
        countNotifications()
    }

    private val _notifCount = MutableStateFlow(0)
    val notifCount get() = _notifCount.asStateFlow()

    private val _appointments = MutableStateFlow<List<Appointment>>(emptyList())
    val appointments get() = _appointments.asStateFlow()

    private val _patients = MutableStateFlow<List<Patient>>(emptyList())
    val patients get() = _patients.asStateFlow()

    private fun getProfile() = checkRequest(
        request = {
            setLoading()
            getDoctorProfile()
        }
    ){
        hideLoading()
        when(it){
            is BaseResult.Success -> {
                val doctorProfile = it.data as DoctorProfile
                _profile.value = doctorProfile
                getAppointments(doctorProfile.doctorID)

            }
            is BaseResult.Error -> {

            }
        }
    }

    fun countNotifications() = checkListRequest(
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

    fun getAppointments(doctorId: String) = checkListRequest(
        request = {
            getDoctorAppointments(doctorId)
        }
    ){result ->
        when(result){
            is BaseResult.Success -> {
                val appointmentList =  result.data as List<Appointment>
                _appointments.value = appointmentList
                _patients.value = appointmentList.map { it.patient }
            }
            is BaseResult.Error -> {

            }
        }
    }

    fun updateAppointmentStatus(appointment: Appointment){
        checkRequest(
            request = {
                updateAppointment(appointment)
            }
        ) {
            when(it){
                is BaseResult.Success -> {
                    getAppointments(appointment.doctor.id)
                }
                is BaseResult.Error -> {

                }
            }
        }
    }

    fun updateProfile(doctorProfile: DoctorProfile) = checkRequest(
        request = {
            updateDoctorProfile(doctorProfile)
        }
    ){
        when(it){
            is BaseResult.Success -> {
                _profile.value = it.data as DoctorProfile
            }
            is BaseResult.Error -> {}
        }
    }
}
package com.example.healthcare.peresentation.appointment.viewmodels

import com.example.healthcare.domain.appointment.Appointment
import com.example.healthcare.domain.appointment.usecase.GetDoctorAppointmentsUseCase
import com.example.healthcare.domain.appointment.usecase.GetPatientAppointmentsUseCase
import com.example.healthcare.domain.appointment.usecase.UpdateAppointmentUseCase
import com.example.healthcare.domain.appointment.util.AppointmentStatus
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.peresentation.auth.utils.Role
import com.example.healthcare.peresentation.common.utils.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppointmentListViewModel(
    private val updateAppointment: UpdateAppointmentUseCase,
    private val getDoctorApps: GetDoctorAppointmentsUseCase,
    private val getPatientApps: GetPatientAppointmentsUseCase,
    id: String,
    role: Role
) : BaseViewModel() {

    init {
        when(role){
            Role.Doctor -> getDoctorAppointments(id)
            Role.Patient -> getPatientAppointments(id)
            else -> {}
        }
    }

    private val _appointmentList = MutableStateFlow<List<Appointment>>(emptyList())
    val appointmentList = _appointmentList.asStateFlow()


    fun updateAppointmentStatus(appointment: Appointment) {
        checkRequest(
            request = {
                updateAppointment(appointment)
            }
        ){
            when(it){
                is BaseResult.Success -> {

                }
                is BaseResult.Error -> {

                }
            }
        }
    }



    fun getDoctorAppointments(doctorID: String) {
        setLoading()
        checkListRequest(
            request = {
                getDoctorApps(doctorID)
            }
        ){
            hideLoading()
            when(it){
                is BaseResult.Success -> {
                    (it.data as List<Appointment>).also { _appointmentList.value = it }
                }
                is BaseResult.Error -> {

                }
            }
        }
    }

    fun getPatientAppointments(patientID: String) {
        setLoading()
        checkListRequest(
            request = {
                getPatientApps(patientID)
            }
        ){
            hideLoading()
            when(it){
                is BaseResult.Success -> {
                    _appointmentList.value = it.data as List<Appointment>
                }
                is BaseResult.Error -> {

                }
            }
        }
    }
}
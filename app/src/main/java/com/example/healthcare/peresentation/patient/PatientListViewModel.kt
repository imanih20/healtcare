package com.example.healthcare.peresentation.patient

import com.example.healthcare.domain.appointment.Appointment
import com.example.healthcare.domain.appointment.usecase.GetDoctorAppointmentsUseCase
import com.example.healthcare.domain.appointment.usecase.GetPatientAppointmentsUseCase
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.patient.Patient
import com.example.healthcare.domain.patient.usecase.GetClinicPatientsUseCase
import com.example.healthcare.peresentation.auth.utils.Role
import com.example.healthcare.peresentation.common.utils.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PatientListViewModel(
    private val getDoctorAppointments: GetDoctorAppointmentsUseCase,
    private val getClinicPatientsUseCase: GetClinicPatientsUseCase,
    id: String,
    role: Role
) : BaseViewModel() {
    private val _patients = MutableStateFlow<List<Patient>>(emptyList())
    val patients = _patients.asStateFlow()

    init {
        if (role == Role.Admin){
            getPatients(id)
        }else if (role == Role.Doctor){
            getDoctorPatients(id)
        }
    }

    fun getDoctorPatients(doctorId: String){
        checkListRequest(
            request = {
                getDoctorAppointments(doctorId)
            }
        ){result ->
            when(result){
                is BaseResult.Success -> {
                    _patients.value = (result.data as List<Appointment>).map {
                        it.patient
                    }
                }
                is BaseResult.Error -> {

                }
            }
        }
    }

    fun getPatients(clinicID: String){
        checkListRequest(
            request = {
                getClinicPatientsUseCase(clinicID)
            }
        ){
            when(it){
                is BaseResult.Success -> {
                    _patients.value = it.data as List<Patient>
                }
                is BaseResult.Error -> {

                }
            }
        }
    }
}
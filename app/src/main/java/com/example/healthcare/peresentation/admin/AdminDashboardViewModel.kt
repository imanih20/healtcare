package com.example.healthcare.peresentation.admin

import android.util.Log
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.doctor.Doctor
import com.example.healthcare.domain.doctor.usecase.GetClinicDoctorsUseCase
import com.example.healthcare.domain.notification.usecase.GetUnreadNotificationsUseCase
import com.example.healthcare.domain.patient.Patient
import com.example.healthcare.domain.patient.usecase.GetClinicPatientsUseCase
import com.example.healthcare.domain.profile.models.AdminProfile
import com.example.healthcare.domain.profile.usecase.GetAdminProfileUseCase
import com.example.healthcare.domain.profile.usecase.UpdateAdminProfileUseCase
import com.example.healthcare.peresentation.common.utils.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AdminDashboardViewModel(
    private val getAdminProf : GetAdminProfileUseCase,
    private val getClinicPatients: GetClinicPatientsUseCase,
    private val getClinicDoctors: GetClinicDoctorsUseCase,
    private val updateAdminProfileUseCase: UpdateAdminProfileUseCase,
    private val getUnreadNotifs : GetUnreadNotificationsUseCase
) : BaseViewModel(){
    private val _adminProfile = MutableStateFlow(AdminProfile())
    val adminProfile get ()= _adminProfile.asStateFlow()

    private val _clinicPatients = MutableStateFlow<List<Patient>>(emptyList())
    val clinicPatients get()= _clinicPatients.asStateFlow()

    private val _clinicDoctors = MutableStateFlow<List<Doctor>>(emptyList())
    val clinicDoctors get()= _clinicDoctors.asStateFlow()

    private val _notifCount = MutableStateFlow(0)
    val notifCount get()= _notifCount.asStateFlow()

    init {
        getAdminProfile()
        getUnreadNotifications()
    }

    private fun getUnreadNotifications(){
        checkListRequest(
            request = { getUnreadNotifs() }
        ){
            when(it){
                is BaseResult.Success -> {
                    _notifCount.value = (it.data as List<*>).size
                }
                is BaseResult.Error -> {}
            }
        }
    }


    private fun getAdminProfile() {
        setLoading()
        checkRequest(
            request = {
                getAdminProf()
            }
        ){
            hideLoading()
            when(it){
                is BaseResult.Success -> {
                    val profile = it.data as AdminProfile
                    _adminProfile.value = profile
                    Log.i("admin profile",_adminProfile.value.clinicName)
                    getPatients(profile.clinicID)
                    getDoctors(profile.clinicID)
                }
                is BaseResult.Error -> {
                }
            }
        }
    }

    private fun getPatients(clinicId: String) {
        checkListRequest(
            request = {
                getClinicPatients(clinicId)
            }
        ){
            when(it){
                is BaseResult.Success -> {
                    _clinicPatients.value = it.data as List<Patient>
                }
                is BaseResult.Error -> {
                }
            }
        }
    }

    private fun getDoctors(clinicId: String) {
        checkListRequest(
            request = {
                getClinicDoctors(clinicId)
            }
        ){
            when(it){
                is BaseResult.Success -> {
                    _clinicDoctors.value = it.data as List<Doctor>
                }
                is BaseResult.Error -> {
                }
            }
        }
    }

    fun updateProfile(
        adminProfile: AdminProfile
    ){
        checkRequest(
            request = {
                updateAdminProfileUseCase(adminProfile)
            }
        ){
            when(it){
                is BaseResult.Success -> _adminProfile.value = it.data as AdminProfile
                is BaseResult.Error -> {

                }
            }
        }
    }
}
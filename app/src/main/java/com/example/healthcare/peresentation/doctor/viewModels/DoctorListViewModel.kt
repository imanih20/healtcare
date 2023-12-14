package com.example.healthcare.peresentation.doctor.viewModels

import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.doctor.Doctor
import com.example.healthcare.domain.doctor.usecase.GetClinicDoctorsUseCase
import com.example.healthcare.peresentation.common.utils.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DoctorListViewModel(
    private val getClinicDoctors: GetClinicDoctorsUseCase,
    clinicId: String
) : BaseViewModel() {
    private val _doctors = MutableStateFlow<List<Doctor>>(emptyList())
    val doctors get() = _doctors.asStateFlow()

    init {
        getDoctors(clinicId)
    }

    private fun getDoctors(clinicId: String){
        checkListRequest(
            request = {
                getClinicDoctors(clinicId)
            }
        ){
            when(it){
                is BaseResult.Success -> {
                    _doctors.value = it.data as List<Doctor>
                }
                is BaseResult.Error -> {

                }
            }
        }
    }
}
package com.example.healthcare.peresentation.prescription

import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.prescription.Prescription
import com.example.healthcare.domain.prescription.usecase.GetPatientPrescriptionsUseCase
import com.example.healthcare.peresentation.common.utils.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PrescriptionListViewModel(
    private val getPatientPrescriptions: GetPatientPrescriptionsUseCase,
    patientId: String
) : BaseViewModel() {
    private val _prescriptions = MutableStateFlow<List<Prescription>>(emptyList())
    val prescription get() = _prescriptions.asStateFlow()

    init {
        getPrescriptions(patientId)
    }

    fun getPrescriptions(patientId: String){
        checkListRequest(
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
    }
}
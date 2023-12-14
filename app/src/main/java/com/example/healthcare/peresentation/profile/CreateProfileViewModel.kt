package com.example.healthcare.peresentation.profile

import com.example.healthcare.domain.clinic.Clinic
import com.example.healthcare.domain.clinic.usecase.CreateClinicUseCase
import com.example.healthcare.domain.clinic.usecase.GetAllClinicsUseCase
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.doctor.usecase.CreateDoctorUseCase
import com.example.healthcare.domain.patient.usecase.CreatePatientUseCase
import com.example.healthcare.peresentation.common.utils.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateProfileViewModel(
    private val createDoctor: CreateDoctorUseCase,
    private val createPatient: CreatePatientUseCase,
    private val createClinic: CreateClinicUseCase,
    private val getAllClinics: GetAllClinicsUseCase
) :BaseViewModel(){

    private val _createRequestState = MutableStateFlow<RequestState>(RequestState.Init)
    val createRequestState = _createRequestState.asStateFlow()

    private val _clinicList = MutableStateFlow<List<Clinic>>(emptyList())
    val clinicList = _clinicList.asStateFlow()

    fun createDoctorProfile(
        clinicID: String,
        specialty: String,
        degree: String,
        profileDescription: String
    ){
        setLoading()
        checkRequest(
            request = {
                createDoctor(
                    clinicID,
                    specialty,
                    degree,
                    profileDescription
                )
            }
        ) {
            hideLoading()
            when(it){
                is BaseResult.Success -> {
                    _createRequestState.value = RequestState.Success
                }
                is BaseResult.Error -> {
                    _createRequestState.value = RequestState.Error(it.rawResponse.message)
                }
            }
        }
    }

    fun createPatientProfile(
        medicalHistory: String,
        age: Int,
        height: Double,
        weight: Double
    ){
        setLoading()
        checkRequest(
            request = {
                createPatient(
                    age,medicalHistory, height, weight
                )
            }
        ){
            hideLoading()
            when(it){
                is BaseResult.Success -> {
                    _createRequestState.value = RequestState.Success
                }
                is BaseResult.Error -> {
                    _createRequestState.value = RequestState.Error(it.rawResponse.message)
                }
            }
        }
    }

    fun createAdminProfile(
        clinicName: String,
        clinicAddress: String,
        clinicCity: String,
        clinicDescription: String
    ){
        setLoading()
        checkRequest(
            request = {
                createClinic(
                    clinicName,clinicAddress,clinicCity,clinicDescription
                )
            }
        ){
            hideLoading()
            when(it){
                is BaseResult.Success -> {
                    _createRequestState.value = RequestState.Success
                }
                is BaseResult.Error -> {
                    _createRequestState.value = RequestState.Error(it.rawResponse.message)
                }
            }
        }
    }

    fun getClinicList(){
        checkListRequest(request = {
            getAllClinics()
        }){
            when(it){
                is BaseResult.Success -> {
                    _clinicList.value = it.data as List<Clinic>
                }
                is BaseResult.Error -> {

                }
            }
        }
    }
}
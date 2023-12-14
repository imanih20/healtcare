package com.example.healthcare.peresentation.medicalRecord

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.medicalRecord.MedicalRecord
import com.example.healthcare.domain.medicalRecord.usecase.CreateMedicalRecordUseCase
import com.example.healthcare.domain.medicalRecord.usecase.DeleteMedicalRecordUseCase
import com.example.healthcare.domain.medicalRecord.usecase.GetPatientMedicalRecordsUseCase
import com.example.healthcare.peresentation.common.utils.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File

class MedicalRecordViewModel(
    private val getPatientMedicalRecords: GetPatientMedicalRecordsUseCase,
    private val createMedicalRecord: CreateMedicalRecordUseCase,
    private val deleteMedicalRecord: DeleteMedicalRecordUseCase,
    private val patientId: String
) : BaseViewModel() {
    private val _records = MutableStateFlow<ArrayList<MedicalRecord>>(arrayListOf())
    val records = _records.asStateFlow()

    init {
        getPatientRecords(patientId)
    }

    fun getPatientRecords(patientId: String){
        checkListRequest(
            request = {
                getPatientMedicalRecords(patientId)
            }
        ) {
            when(it){
                is BaseResult.Success -> {
                    _records.value = it.data as ArrayList<MedicalRecord>
                }
                is BaseResult.Error -> {

                }
            }
        }
    }

    fun addRecord(
        fileUri: String,
        patientId: String,
        recordType: String,
        date: String,
        notes: String
    ){
        val file = File(fileUri)
        checkRequest(
            request = {
                createMedicalRecord(
                    file,
                    patientId,
                    recordType,
                    date,
                    notes
                )
            }
        ){
            when(it){
                is BaseResult.Success -> {
                    _records.value.add(it.data as MedicalRecord)
                }
                is BaseResult.Error -> {
                }
            }
        }
    }

    fun deleteRecords(idList: List<String>){
        checkListRequest(
            request = {
                deleteMedicalRecord(idList)
            }
        ){result ->
            when(result){
                is BaseResult.Success -> {
                    _records.value.removeIf {
                        it.id in (result.data as List<String>)
                    }
                }
                is BaseResult.Error -> {

                }
            }
        }
    }

}
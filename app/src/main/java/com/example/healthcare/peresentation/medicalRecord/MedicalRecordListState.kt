package com.example.healthcare.peresentation.medicalRecord

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import com.example.healthcare.common.falseAll
import com.example.healthcare.domain.medicalRecord.MedicalRecord

@Composable
fun rememberMedicalRecordListState(list: List<MedicalRecord>) : MedicalRecordListState {
    return remember {
        MedicalRecordListState(list)
    }
}

class MedicalRecordListState(list: List<MedicalRecord>) {
    var selectList= mutableStateMapOf<String, Boolean>()

    init {
        initialize(list)
    }

    fun initialize(list: List<MedicalRecord>){
        for (item in list){
            selectList[item.id] = false
        }
    }

    fun unSelectAll() {
        selectList.falseAll()
    }

    fun toggle(id: String) {
        selectList[id]?.let {
            selectList[id] =!it
        }
    }

    fun countSelected() : Int {
        return selectList.count {
            it.value
        }
    }

    fun getSelected() : List<String> {
        return selectList.filter {
            it.value
        }.keys.toList()
    }

    fun isAnySelected(): Boolean{
        return selectList.values.any{ it }
    }
}
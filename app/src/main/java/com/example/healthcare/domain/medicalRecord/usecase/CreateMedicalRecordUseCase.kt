package com.example.healthcare.domain.medicalRecord.usecase

import android.content.ContentResolver
import com.example.healthcare.data.common.utils.WrappedResponse
import com.example.healthcare.data.medicalRecord.service.MedicalRecordService
import com.example.healthcare.data.medicalRecord.service.dto.MedicalRecordRequest
import com.example.healthcare.data.medicalRecord.service.dto.MedicalRecordResponse
import com.example.healthcare.domain.common.BaseResult
import com.example.healthcare.domain.medicalRecord.MedicalRecord
import com.example.healthcare.domain.medicalRecord.MedicalRecordResult
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class CreateMedicalRecordUseCase(private val service: MedicalRecordService) {
    suspend operator fun invoke(
        file: File,
        patientId: String,
        recordType: String,
        date: String,
        notes: String
    ) : Flow<BaseResult<MedicalRecord,WrappedResponse<MedicalRecordResponse>>> {
        val requestBody = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())

        val response = service.createMedicalRecord(
            MultipartBody.Part.createFormData("file", file.name,requestBody),
            MedicalRecordRequest(
                patientId,
                recordType,
                date,
                notes
            )
        )
        return MedicalRecordResult().getResult(response)
    }
}
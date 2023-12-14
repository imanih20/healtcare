package com.example.healthcare.peresentation.profile

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthcare.peresentation.common.utils.AGE_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.HEIGHT_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.SAVE_BTN
import com.example.healthcare.peresentation.common.utils.WEIGHT_INPUT_LABEL
import com.example.healthcare.peresentation.common.composables.MyButton
import com.example.healthcare.peresentation.common.composables.MyTextField
import com.example.healthcare.peresentation.common.utils.MEDICAL_INPUT_LABEL

@Composable
fun PatientProfile(
    state: PatientProfileState = rememberPatientProfileState(),
    isLoading : Boolean = false,
    onSaveClick: () -> Unit = {}
) {
    MyTextField(
        value = state.age,
        onValueChange = {
            state.age = it
        },
        modifier = Modifier.fillMaxWidth(),
        label = AGE_INPUT_LABEL
    )
    Spacer(modifier = Modifier.size(15.dp))
    MyTextField(
        value = state.weight,
        onValueChange = {
            state.weight = it
        },
        modifier = Modifier.fillMaxWidth(),
        label = WEIGHT_INPUT_LABEL
    )
    Spacer(modifier = Modifier.size(15.dp))
    MyTextField(
        value = state.height,
        onValueChange = {
            state.height = it
        },
        modifier = Modifier.fillMaxWidth(),
        label = HEIGHT_INPUT_LABEL
    )
    Spacer(modifier = Modifier.size(15.dp))
    MyTextField(
        value = state.medicalHistory,
        onValueChange = {
            state.medicalHistory = it
        },
        modifier = Modifier.fillMaxWidth(),
        label = MEDICAL_INPUT_LABEL
    )
    Spacer(modifier = Modifier.size(30.dp))
    MyButton(
        title = SAVE_BTN,
        onClick = onSaveClick,
        modifier = Modifier.fillMaxWidth(),
        isLoading = isLoading
    )
}
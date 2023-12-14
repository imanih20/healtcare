package com.example.healthcare.peresentation.profile

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthcare.domain.clinic.Clinic
import com.example.healthcare.peresentation.common.utils.DOCTOR_DEGREE_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.DOCTOR_DESCRIPTION_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.DOCTOR_SPECIALITY_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.SAVE_BTN
import com.example.healthcare.peresentation.common.composables.MyButton
import com.example.healthcare.peresentation.common.composables.MyTextField
import com.example.healthcare.peresentation.common.composables.Spinner

@Composable
fun DoctorProfile(
    state: DoctorProfileState = rememberDoctorProfileState(),
    isLoading: Boolean = false,
    clinicList: List<Clinic>,
    onSaveClick: () -> Unit = {}
) {
    MyTextField(
        value = state.degree,
        onValueChange = {
            state.degree = it
        },
        modifier = Modifier.fillMaxWidth(),
        label = DOCTOR_DEGREE_INPUT_LABEL
    )
    Spacer(modifier = Modifier.size(15.dp))
    MyTextField(
        value = state.speciality,
        onValueChange = {
            state.speciality = it
        },
        modifier = Modifier.fillMaxWidth(),
        label = DOCTOR_SPECIALITY_INPUT_LABEL
    )
    Spacer(modifier = Modifier.size(15.dp))
    MyTextField(
        value = state.description,
        onValueChange = {
            state.description = it
        },
        modifier = Modifier.fillMaxWidth(),
        label = DOCTOR_DESCRIPTION_INPUT_LABEL
    )
    Spacer(modifier = Modifier.size(20.dp))
    Spinner(
        modifier = Modifier.fillMaxWidth(),
        options = clinicList,
        selectedIndex = 0,
        onSelectedItem = {
        state.clinicID = it.id
    })
    Spacer(modifier = Modifier.size(30.dp))
    MyButton(
        title = SAVE_BTN,
        onClick = onSaveClick,
        modifier = Modifier.fillMaxWidth(),
        isLoading = isLoading
    )
}
package com.example.healthcare.peresentation.profile

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthcare.peresentation.common.utils.CLINIC_ADDR_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.CLINIC_NAME_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.SAVE_BTN
import com.example.healthcare.peresentation.common.composables.MyButton
import com.example.healthcare.peresentation.common.composables.MyTextField
import com.example.healthcare.peresentation.common.utils.CLINIC_city_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.CLINIC_DESCRIPTION_INPUT_LABEL

@Composable
fun AdminProfile(
   state: AdminProfileState = rememberAdminProfileState(),
   isLoading: Boolean = false,
   onSaveClick: () -> Unit = {}
) {
   MyTextField(
      value = state.clinicName,
      onValueChange = {
         state.clinicName = it
      },
      modifier = Modifier.fillMaxWidth(),
      CLINIC_NAME_INPUT_LABEL
   )
   Spacer(modifier = Modifier.size(15.dp))
   MyTextField(
      value = state.clinicAddress,
      onValueChange = {
         state.clinicAddress = it
      },
      modifier = Modifier.fillMaxWidth(),
      CLINIC_ADDR_INPUT_LABEL
   )
   Spacer(modifier = Modifier.size(15.dp))
   MyTextField(
      value = state.clinicCity,
      onValueChange = {
         state.clinicCity = it
      },
      modifier = Modifier.fillMaxWidth(),
      CLINIC_city_INPUT_LABEL
   )
   Spacer(modifier = Modifier.size(15.dp))
   MyTextField(
      value = state.clinicDescription,
      onValueChange = {
         state.clinicDescription = it
      },
      modifier = Modifier.fillMaxWidth(),
      CLINIC_DESCRIPTION_INPUT_LABEL,
      singleLine = false,
      maxLines = 4
   )
   Spacer(modifier = Modifier.size(30.dp))
   MyButton(
      title = SAVE_BTN,
      onClick = onSaveClick,
      modifier = Modifier.fillMaxWidth(),
      isLoading = isLoading
   )
}
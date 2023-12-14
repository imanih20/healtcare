package com.example.healthcare.peresentation.common.composables

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.healthcare.domain.patient.Patient
import com.example.healthcare.peresentation.common.composables.ProfileImage

@Composable
fun PatientListItem(
    patient: Patient
) {
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),) {
        ListItem(
            headlineContent = {
                Text(text = "${patient.firstName} ${patient.lastName}")
            },
            leadingContent = {
                ProfileImage(profileUrl = patient.profile)
            },
            supportingContent = {

            }
        )
    }
}
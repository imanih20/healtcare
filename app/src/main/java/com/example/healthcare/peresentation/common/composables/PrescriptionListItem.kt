package com.example.healthcare.peresentation.common.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.healthcare.domain.prescription.Prescription

@Composable
fun PrescriptionListItem(
    prescription: Prescription
) {
    Card {
        ListItem(
            headlineContent = {
                Text(text = prescription.medicine)
            },
            supportingContent = {
                Text(text = prescription.instructions)
            },
            trailingContent = {
                Text(text = "${prescription.dosage} every ${prescription.frequency}")
            }
        )
        Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            Text(text = "From ${prescription.startDate} to ${prescription.endDate}")
        }
    }
}
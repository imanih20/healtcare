package com.example.healthcare.peresentation.common.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthcare.domain.appointment.Appointment
import com.example.healthcare.domain.appointment.util.AppointmentStatus
import com.example.healthcare.domain.doctor.Doctor
import com.example.healthcare.domain.patient.Patient
import java.util.Date

@Composable
fun PatientAppointmentListItem(
    appointment: Appointment,
    onCancel: ()->Unit
) {
    Card(colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.background
    )) {
        ListItem(
            headlineContent = {
                Text(text = "Dr. "+appointment.doctor.firstName + " " + appointment.doctor.lastName)
            },
            leadingContent = {
                ProfileImage(profileUrl = appointment.doctor.profileUrl)
            },
            supportingContent = {
                Text(text = appointment.doctor.specialty)
            },
            trailingContent = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AppointmentStatusCompose(appointmentStatus = appointment.status)
                    if (appointment.status == AppointmentStatus.REQUESTED
                        || appointment.status == AppointmentStatus.ACCEPTED
                    ) TextButton(onClick = onCancel) {
                        Text(text = "Cancel")
                    }
                }
            }
        )
        Box(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(), contentAlignment = Alignment.Center){
            Text(text = Date(appointment.dateTime).toString(), color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Preview
@Composable
fun PatientAppointmentListItemPreview() {
    PatientAppointmentListItem(appointment = Appointment(
        "",
        Doctor(
            "",
            "",
            "John",
            "Do",
            "",
            "",
            "",
            "",
            "General",
            "",
            ""
        ),
        Patient(
            "",
            "",
            "John",
            "Do",
            "",
            40,
            40.0,
            160.0
        ),
        150000,
        AppointmentStatus.REQUESTED
    )){}
}
package com.example.healthcare.peresentation.common.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthcare.domain.appointment.Appointment
import com.example.healthcare.domain.appointment.util.AppointmentStatus
import com.example.healthcare.domain.doctor.Doctor
import com.example.healthcare.domain.patient.Patient
import com.example.healthcare.peresentation.common.utils.DoctorAppointmentActions
import java.util.Date

@Composable
fun DoctorAppointmentListItem(
    appointment: Appointment,
    onAction: (DoctorAppointmentActions)->Unit
) {
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),) {
        ListItem(
            headlineContent = {
                Text(
                    text = "${appointment.patient.firstName} ${appointment.patient.lastName}",
                )
            },
            leadingContent = {
                ProfileImage(profileUrl = appointment.patient.profile)
            },
            trailingContent = {
                if (appointment.status == AppointmentStatus.REQUESTED) {
                    Column {
                        TextButton(
                            onClick = { onAction(DoctorAppointmentActions.Accept) },
                            border = BorderStroke(1.dp, Color.Green),
                            modifier = Modifier.width(80.dp),
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = Color.Green
                            )
                        ) {
                            Text(text = "Accept")
                        }
                        TextButton(
                            onClick = { onAction(DoctorAppointmentActions.Reject) },
                            border = BorderStroke(1.dp, Color.Red),
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = Color.Red
                            ),
                            modifier = Modifier.width(80.dp)
                        ) {
                            Text(text = "Reject")
                        }
                    }
                } else {
                    Column {
                        AppointmentStatusCompose(appointmentStatus = appointment.status)
                    }
                }
            }
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = Date(appointment.dateTime).toString(), style = MaterialTheme.typography.bodySmall)
            TextButton(onClick = { onAction(DoctorAppointmentActions.Cancel) }) {
                Text(text = "Cancel")
            }
        }
    }
}

@Preview
@Composable
fun DoctorAppointmentListItemPreview() {
    DoctorAppointmentListItem(appointment = Appointment(
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
        AppointmentStatus.ACCEPTED
    )){}
}
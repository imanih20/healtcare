package com.example.healthcare.peresentation.common.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.healthcare.domain.appointment.util.AppointmentStatus

@Composable
fun AppointmentStatusCompose(
    appointmentStatus: AppointmentStatus
) {
    val color = when(appointmentStatus){
        AppointmentStatus.REQUESTED -> Color.Yellow
        AppointmentStatus.REJECTED -> MaterialTheme.colorScheme.error
        AppointmentStatus.CANCELLED -> MaterialTheme.colorScheme.error
        AppointmentStatus.ACCEPTED -> Color.Green
        AppointmentStatus.COMPLETED -> Color.Gray
        AppointmentStatus.INPROGRESS -> Color.Blue
        else -> {
            MaterialTheme.colorScheme.onBackground
        }
    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .border(1.dp, color, RoundedCornerShape(5.dp))
            .padding(5.dp)
    ){
        Text(text = appointmentStatus.value, fontSize = 14.sp, color = color)
    }
}
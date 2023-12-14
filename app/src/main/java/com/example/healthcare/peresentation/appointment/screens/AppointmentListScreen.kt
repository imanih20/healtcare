package com.example.healthcare.peresentation.appointment.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.healthcare.domain.appointment.Appointment
import com.example.healthcare.domain.appointment.util.AppointmentStatus
import com.example.healthcare.peresentation.appointment.viewmodels.AppointmentListViewModel
import com.example.healthcare.peresentation.auth.utils.Role
import com.example.healthcare.peresentation.common.composables.BackLayoutV1
import com.example.healthcare.peresentation.common.composables.MyScaffold
import com.example.healthcare.peresentation.common.composables.DoctorAppointmentListItem
import com.example.healthcare.peresentation.common.composables.PatientAppointmentListItem
import com.example.healthcare.peresentation.common.utils.DoctorAppointmentActions
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Destination
@Composable
fun AppointmentListScreen(
    role: Role,
    id: String = "",
    viewModel: AppointmentListViewModel = koinViewModel(parameters = { parametersOf(id, role) })
) {
    val appointmentList = viewModel.appointmentList.collectAsState()

    AppointmentListScreenContent(
        appointmentList.value,
        role,
        updateAppointment = {
            viewModel.updateAppointmentStatus(it)
        }
    )
}

@Composable
fun AppointmentListScreenContent(
    appointmentList : List<Appointment> = emptyList(),
    role: Role = Role.Patient,
    updateAppointment: (Appointment)->Unit = {}
) {
    MyScaffold {
        BackLayoutV1(
            headerPosition = 0.08f,
            circleSize = 0.0f
        ) {
            if(appointmentList.isEmpty()){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "No Appointments Available")
                }
            } else {
                LazyColumn{
                    items(appointmentList){ app ->
                        when(role){
                            Role.Patient -> PatientAppointmentListItem(appointment = app){
                                updateAppointment(app.copy(status = AppointmentStatus.CANCELLED))
                            }
                            Role.Doctor -> DoctorAppointmentListItem(appointment = app){
                                updateAppointment(app.copy(status = when(it){
                                    DoctorAppointmentActions.Accept -> AppointmentStatus.ACCEPTED
                                    DoctorAppointmentActions.Cancel -> AppointmentStatus.CANCELLED
                                    DoctorAppointmentActions.Reject -> AppointmentStatus.REJECTED
                                }))
                            }
                            else -> {}
                        }
                        Divider()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AppointmentListScreenPreview() {
    AppointmentListScreenContent()
}
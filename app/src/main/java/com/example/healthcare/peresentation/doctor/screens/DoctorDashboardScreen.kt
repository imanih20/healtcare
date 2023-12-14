package com.example.healthcare.peresentation.doctor.screens

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.healthcare.domain.appointment.Appointment
import com.example.healthcare.domain.appointment.util.AppointmentStatus
import com.example.healthcare.domain.patient.Patient
import com.example.healthcare.domain.profile.models.DoctorProfile
import com.example.healthcare.peresentation.auth.utils.Role
import com.example.healthcare.peresentation.common.composables.BackLayoutV1
import com.example.healthcare.peresentation.common.composables.BadgeIcon
import com.example.healthcare.peresentation.common.composables.ProfileImage
import com.example.healthcare.peresentation.common.composables.DetailRow
import com.example.healthcare.peresentation.common.composables.DoctorAppointmentListItem
import com.example.healthcare.peresentation.common.composables.MyButton
import com.example.healthcare.peresentation.common.composables.MyTextField
import com.example.healthcare.peresentation.common.composables.PatientListItem
import com.example.healthcare.peresentation.common.composables.ShowCard
import com.example.healthcare.peresentation.common.utils.ADDRESS_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.city_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.DOCTOR_DESCRIPTION_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.DOCTOR_SPECIALITY_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.DoctorAppointmentActions
import com.example.healthcare.peresentation.common.utils.FIRST_NAME_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.LAST_NAME_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.POSTAL_CODE_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.SAVE_BTN
import com.example.healthcare.peresentation.destinations.AppointmentListScreenDestination
import com.example.healthcare.peresentation.destinations.PatientListScreenDestination
import com.example.healthcare.peresentation.doctor.viewModels.DoctorDashboardState
import com.example.healthcare.peresentation.doctor.viewModels.DoctorDashboardViewModel
import com.example.healthcare.peresentation.doctor.viewModels.rememberDoctorDashboardState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.Bell
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun DoctorDashboardScreen(
    navigator: DestinationsNavigator,
    viewModel: DoctorDashboardViewModel = koinViewModel()
) {
    val notificationCount = viewModel.notifCount.collectAsState()
    val profile = viewModel.profile.collectAsState()
    val appointments = viewModel.appointments.collectAsState()
    val patients = viewModel.patients.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()

    DoctorDashboardScreenContent(
        doctorProfile = profile.value,
        appointmentList = appointments.value,
        patientList = patients.value,
        notificationCount = notificationCount.value,
        isLoading = isLoading.value,
        navigateToNotifications = {
            navigator.popBackStack()
        },
        onAppointmentActionClick = {
            viewModel.updateAppointmentStatus(it)
        },
        onAppointmentPressed = {id->
            navigator.navigate(AppointmentListScreenDestination(
                id =id ,
                role = Role.Doctor
            ))
        },
        onPatientsPressed = {id->
            navigator.navigate(PatientListScreenDestination(Role.Doctor,id))
        },
        onSaveClick = {
            viewModel.updateProfile(it)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorDashboardScreenContent(
    doctorProfile: DoctorProfile,
    state : DoctorDashboardState = rememberDoctorDashboardState(),
    appointmentList: List<Appointment> = emptyList(),
    patientList: List<Patient> = emptyList(),
    notificationCount: Int = 0,
    isLoading: Boolean = false,
    navigateToNotifications: ()->Unit = {},
    onAppointmentActionClick: (Appointment)->Unit = {},
    onAppointmentPressed: (String)->Unit = {},
    onPatientsPressed: (String)->Unit = {},
    onSaveClick: (DoctorProfile) -> Unit = {}
) {

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()
    state.init(doctorProfile)
    BottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        scaffoldState = scaffoldState,
        sheetShadowElevation = 15.dp,
        sheetContent = {
            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                MyTextField(
                    value = state.firstName,
                    onValueChange = {
                        state.firstName = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = FIRST_NAME_INPUT_LABEL,
                )
                Spacer(modifier = Modifier.size(15.dp))
                MyTextField(
                    value = state.lastName,
                    onValueChange = {
                        state.lastName = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = LAST_NAME_INPUT_LABEL
                )
                Spacer(modifier = Modifier.size(15.dp))
                MyTextField(
                    value = state.address,
                    onValueChange = {
                        state.address = it
                    },
                    Modifier.fillMaxWidth(),
                    label = ADDRESS_INPUT_LABEL
                )
                Spacer(modifier = Modifier.size(15.dp))
                Row {
                    MyTextField(
                        value = state.city,
                        onValueChange = {
                            state.city = it
                        },
                        Modifier.weight(3f),
                        label = city_INPUT_LABEL
                    )
                    Spacer(modifier = Modifier.size(7.dp))
                    MyTextField(
                        value = state.postalCode,
                        onValueChange = {
                            state.postalCode = it
                        },
                        Modifier.weight(2f),
                        label = POSTAL_CODE_INPUT_LABEL
                    )
                }
                Spacer(modifier = Modifier.size(15.dp))
                MyTextField(
                    value = state.specialty,
                    onValueChange = {
                        state.specialty = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    DOCTOR_SPECIALITY_INPUT_LABEL
                )
                Spacer(modifier = Modifier.size(15.dp))
                MyTextField(
                    value = state.profileDescription,
                    onValueChange = {
                        state.profileDescription = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    DOCTOR_DESCRIPTION_INPUT_LABEL
                )
                Spacer(modifier = Modifier.size(30.dp))
                MyButton(
                    title = SAVE_BTN,
                    onClick = {
                        if (state.checkIfEdited(doctorProfile)){
                            onSaveClick(doctorProfile.copy(
                                firstName = state.firstName,
                                lastName = state.lastName,
                                city = state.city,
                                address = state.address,
                                postalCode = state.postalCode,
                                specialty = state.specialty,
                                profileDescription = state.profileDescription
                            ))
                            scope.launch {
                                scaffoldState.bottomSheetState.hide()
                            }
                        }
                    },
                    enabled = state.checkIfEdited(doctorProfile),
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = isLoading
                )
            }
        },
    ) {
        BackLayoutV1(
            headerPosition = 0.08f,
            circleSize = 0.1f,
            circleXPosition = 0.921f,
            headerContent = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = "Dashboard")
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    actions = {
                        BadgeIcon(icon = FontAwesomeIcons.Regular.Bell,notificationCount) {
                            navigateToNotifications()
                        }
                    }
                )
            }
        ){
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(5.dp)
                    .scrollable(rememberScrollState(), Orientation.Vertical, enabled = true)
            ) {
                ShowCard(
                    title = "Personal Information",
                    actionIcon = Icons.Default.Edit, onActionClick = {
                        scope.launch {
                            scaffoldState.bottomSheetState.expand()
                        }
                    }
                ) {
                    Row {
                        Column(
                            Modifier
                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            ProfileImage(profileUrl = doctorProfile.profile,size = 100.dp)
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(text = "${doctorProfile.firstName} ${doctorProfile.lastName}", fontSize = 20.sp,)
                        }

                        Column {
                            DetailRow(title = "Phone Number:", detail = doctorProfile.phone)
                            DetailRow(title = "Address:", detail = "${doctorProfile.city}, ${doctorProfile.address}, ${doctorProfile.postalCode}")
                            DetailRow(title = "Specialty", detail = doctorProfile.specialty)
                            DetailRow(title = "Description", detail = doctorProfile.profileDescription )
                        }
                    }
                }
                Spacer(modifier = Modifier.size(15.dp))
                ShowCard(
                    title = "Appointments",
                    actionText = "Show All",
                    onActionClick = { onAppointmentPressed(doctorProfile.doctorID) }) {
                    if(appointmentList.isEmpty()){
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(50.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "You don't have any appointment.", fontSize = 15.sp, textAlign = TextAlign.Center)
                        }
                    }else {
                        LazyColumn{
                            items(3){ item ->
                                val appointment = appointmentList[item]
                                DoctorAppointmentListItem(appointment = appointment) {
                                    val status = when(it){
                                        DoctorAppointmentActions.Accept -> AppointmentStatus.ACCEPTED
                                        DoctorAppointmentActions.Reject -> AppointmentStatus.REJECTED
                                        DoctorAppointmentActions.Cancel -> AppointmentStatus.CANCELLED
                                    }
                                    onAppointmentActionClick(appointment.copy(status = status))
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.size(15.dp))
                ShowCard(
                    title = "Patients",
                    actionText = "Show All",
                    onActionClick = { onPatientsPressed(doctorProfile.doctorID) }) {
                    if(patientList.isEmpty()){
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(50.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "You don't have any patient.", fontSize = 15.sp, textAlign = TextAlign.Center)
                        }
                    } else {
                        LazyColumn {
                            items(3){
                                val patient = patientList[it]
                                PatientListItem(patient = patient)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DoctorDashboardPreview() {
    val doctorProfile = DoctorProfile("", "", "John", "Doe", "+698755555", "Iran", "Tehran", "12345", "", "", "", "General", "", "")
    DoctorDashboardScreenContent(doctorProfile = doctorProfile){}
}
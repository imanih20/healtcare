package com.example.healthcare.peresentation.patient

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.healthcare.domain.appointment.Appointment
import com.example.healthcare.domain.appointment.util.AppointmentStatus
import com.example.healthcare.domain.medicalRecord.MedicalRecord
import com.example.healthcare.domain.prescription.Prescription
import com.example.healthcare.domain.profile.models.PatientProfile
import com.example.healthcare.peresentation.auth.utils.Role
import com.example.healthcare.peresentation.common.composables.BackLayoutV1
import com.example.healthcare.peresentation.common.composables.BadgeIcon
import com.example.healthcare.peresentation.common.composables.DetailRow
import com.example.healthcare.peresentation.common.composables.MyButton
import com.example.healthcare.peresentation.common.composables.MyTextField
import com.example.healthcare.peresentation.common.composables.PatientAppointmentListItem
import com.example.healthcare.peresentation.common.composables.PrescriptionListItem
import com.example.healthcare.peresentation.common.composables.RecordListItem
import com.example.healthcare.peresentation.common.composables.ShowCard
import com.example.healthcare.peresentation.common.utils.ADDRESS_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.AGE_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.CLINIC_ADDR_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.CLINIC_city_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.CLINIC_DESCRIPTION_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.CLINIC_NAME_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.city_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.FIRST_NAME_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.HEIGHT_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.LAST_NAME_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.POSTAL_CODE_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.SAVE_BTN
import com.example.healthcare.peresentation.common.utils.WEIGHT_INPUT_LABEL
import com.example.healthcare.peresentation.destinations.AppointmentListScreenDestination
import com.example.healthcare.peresentation.destinations.MedicalRecordListDestination
import com.example.healthcare.peresentation.destinations.NotificationScreenDestination
import com.example.healthcare.peresentation.destinations.PrescriptionListScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.Bell
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun PatientDashboard(
    navigator: DestinationsNavigator,
    viewModel: PatientDashboardViewModel = koinViewModel()
) {
    val profile = viewModel.profile.collectAsState()
    val records = viewModel.records.collectAsState()
    val prescriptions = viewModel.prescription.collectAsState()
    val appointments = viewModel.appointments.collectAsState()
    val notificationCount = viewModel.notifCount.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()

    PatientDashboardContent(
        profile.value,
        appointments.value,
        records.value,
        prescriptions.value,
        notificationCount.value,
        isLoading = isLoading.value,
        navigateToNotifications = {
            navigator.navigate(NotificationScreenDestination)
        },
        onRecordsPressed = {
            navigator.navigate(MedicalRecordListDestination(it))
        },
        onAppointmentsPressed = {
            navigator.navigate(AppointmentListScreenDestination(Role.Patient,it))
        },
        onCancelPressed = {
            viewModel.updateAppointmentStatus(it)
        },
        onDownloadPress = {

        },
        onPrescriptionsPressed = {
            navigator.navigate(PrescriptionListScreenDestination(it))
        },
        onSaveClick = {
            viewModel.updateProfile(it)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientDashboardContent(
    patientProfile: PatientProfile,
    appointmentList: List<Appointment> = emptyList(),
    medicalRecordList: List<MedicalRecord> = emptyList(),
    prescriptionList: List<Prescription> = emptyList(),
    notificationCount: Int = 0,
    isLoading:Boolean = false,
    state : PatientDashboardState = rememberPatientDashboardState(),
    navigateToNotifications:()->Unit = {},
    onRecordsPressed: (String)->Unit = {},
    onAppointmentsPressed: (String)-> Unit = {},
    onCancelPressed: (Appointment) -> Unit = {},
    onDownloadPress: (MedicalRecord) -> Unit = {},
    onPrescriptionsPressed: (String)->Unit = {},
    onSaveClick: (PatientProfile)->Unit = {}

) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    state.init(patientProfile)
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
                    value = state.age,
                    onValueChange = {
                        state.age = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = AGE_INPUT_LABEL
                )
                Spacer(modifier = Modifier.size(15.dp))
                MyTextField(
                    value = state.height,
                    onValueChange = {
                        state.height = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = HEIGHT_INPUT_LABEL
                )
                Spacer(modifier = Modifier.size(15.dp))
                MyTextField(
                    value = state.weight,
                    onValueChange = {
                        state.weight = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = WEIGHT_INPUT_LABEL
                )
                Spacer(modifier = Modifier.size(15.dp))
                MyTextField(
                    value = state.medicalHistory,
                    onValueChange = {
                        state.medicalHistory = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = CLINIC_DESCRIPTION_INPUT_LABEL,
                    singleLine = false,
                    maxLines = 4
                )
                Spacer(modifier = Modifier.size(30.dp))
                MyButton(
                    title = SAVE_BTN,
                    onClick = {
                        if (state.checkIfEdited(patientProfile)){
                            onSaveClick(patientProfile.copy(
                                firstName = state.firstName,
                                lastName = state.lastName,
                                city = state.city,
                                address = state.address,
                                postalCode = state.postalCode,
                                age = state.age.toIntOrNull()?:0,
                                height = state.height.toDoubleOrNull()?:0.0,
                                weight = state.weight.toDoubleOrNull()?:0.0,
                                medicalHistory = state.medicalHistory
                            ))
                            scope.launch {
                                scaffoldState.bottomSheetState.hide()
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = state.checkIfEdited(patientProfile),
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
                        BadgeIcon(icon = FontAwesomeIcons.Regular.Bell, notificationCount) {
                            navigateToNotifications()
                        }
                    }
                )
            }
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(5.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                ShowCard(
                    title = "Personal Information",
                    actionIcon = Icons.Default.Edit, onActionClick = {
                        scope.launch {
                            scaffoldState.bottomSheetState.expand()
                        }
                    }) {
                    DetailRow(title = "Name:", detail = "${patientProfile.firstName} ${patientProfile.lastName}")
                    DetailRow(title = "Phone Number:", detail = patientProfile.phone)
                    DetailRow(title = "Address:", detail = "${patientProfile.city}, ${patientProfile.address}, ${patientProfile.postalCode}")
                    DetailRow(title = "Age: ", detail = "${patientProfile.age} years old")
                    DetailRow(title = "Height", detail = "${patientProfile.height} cm")
                    DetailRow(title = "weight", detail = "${patientProfile.weight} kg")
                }
                Spacer(modifier = Modifier.size(15.dp))
                ShowCard(
                    title = "Medical Records",
                    actionText = "Show All",
                    onActionClick = { onRecordsPressed(patientProfile.patientID) }) {
                    if(appointmentList.isEmpty()){
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(50.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "You don't uploaded any record.", fontSize = 15.sp, textAlign = TextAlign.Center)
                        }
                    }else{
                        LazyColumn{
                            items(3){
                                val record = medicalRecordList[it]
                                RecordListItem(medicalRecord = record){
                                    onDownloadPress(record)
                                }
                            }
                        }
                    }

                }
                Spacer(modifier = Modifier.size(15.dp))
                ShowCard(
                    title = "Appointments",
                    actionText = "Show All",
                    onActionClick = { onAppointmentsPressed(patientProfile.patientID) }) {
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
                            items(3){
                                val appointment = appointmentList[it]
                                PatientAppointmentListItem(appointment = appointment) {
                                    onCancelPressed(appointment.copy(status = AppointmentStatus.CANCELLED))
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.size(15.dp))
                ShowCard(
                    title = "Prescriptions",
                    actionText =  "Show All",
                    onActionClick = { onPrescriptionsPressed(patientProfile.patientID) }) {
                    if(appointmentList.isEmpty()){
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(50.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "You don't have any prescription.", fontSize = 15.sp, textAlign = TextAlign.Center)
                        }
                    }else{
                        LazyColumn{
                            items(3){
                                val prescription = prescriptionList[it]
                                PrescriptionListItem(prescription = prescription)
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
fun PatientDashboardPreview() {
    PatientDashboardContent(
        PatientProfile(
            "",
            "",
            "John",
            "Do",
            "+155555555",
            "Iran",
            "Qeshm",
            "123456",
            "",
            "",
            "",
            "",
            40,
            160.2,
            65.0
        )
    ){}
}
package com.example.healthcare.peresentation.admin

import android.app.Notification
import androidx.compose.foundation.background
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
import com.example.healthcare.domain.doctor.Doctor
import com.example.healthcare.domain.patient.Patient
import com.example.healthcare.domain.profile.models.AdminProfile
import com.example.healthcare.peresentation.auth.utils.Role
import com.example.healthcare.peresentation.common.composables.BackLayoutV1
import com.example.healthcare.peresentation.common.composables.BadgeIcon
import com.example.healthcare.peresentation.common.composables.MyButton
import com.example.healthcare.peresentation.common.composables.MyTextField
import com.example.healthcare.peresentation.common.composables.ProfileImage
import com.example.healthcare.peresentation.common.utils.ADDRESS_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.CLINIC_ADDR_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.CLINIC_city_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.CLINIC_DESCRIPTION_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.CLINIC_NAME_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.city_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.FIRST_NAME_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.LAST_NAME_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.POSTAL_CODE_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.SAVE_BTN
import com.example.healthcare.peresentation.common.composables.DetailRow
import com.example.healthcare.peresentation.common.composables.DoctorListItem
import com.example.healthcare.peresentation.common.composables.PatientListItem
import com.example.healthcare.peresentation.common.composables.ShowCard
import com.example.healthcare.peresentation.destinations.DoctorListScreenDestination
import com.example.healthcare.peresentation.destinations.NotificationScreenDestination
import com.example.healthcare.peresentation.destinations.PatientListScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.Bell
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun AdminDashboard(
    navigator: DestinationsNavigator = EmptyDestinationsNavigator,
    viewModel: AdminDashboardViewModel = koinViewModel()
) {
    val adminProfile = viewModel.adminProfile.collectAsState()
    val patientList = viewModel.clinicPatients.collectAsState()
    val doctorList = viewModel.clinicDoctors.collectAsState()
    val notificationCount = viewModel.notifCount.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()

    AdminDashboardContent(
        adminProfile = adminProfile.value,
        doctorList = doctorList.value,
        patientList = patientList.value,
        notificationCount = notificationCount.value,
        isLoading =  isLoading.value,
        onSaveClick = {
            viewModel.updateProfile(it)
        },
        navigateToDoctorList = {
            navigator.navigate(DoctorListScreenDestination(it))
        },
        navigateToPatientList = {
            navigator.navigate(PatientListScreenDestination(Role.Admin,it))
        },
        navigateToNotifications = {
            navigator.navigate(NotificationScreenDestination)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardContent(
    adminProfile: AdminProfile,
    state: AdminDashboardState = rememberAdminDashboardState(),
    doctorList: List<Doctor> = emptyList(),
    patientList: List<Patient> = emptyList(),
    notificationCount: Int = 0,
    isLoading: Boolean = false,
    onSaveClick: (AdminProfile)->Unit,
    navigateToPatientList: (String)->Unit,
    navigateToDoctorList: (String) -> Unit,
    navigateToNotifications: ()-> Unit
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    state.init(adminProfile)
    val scope = rememberCoroutineScope()
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
                    onClick = {
                        if (state.checkIfEdited(adminProfile)){
                            onSaveClick(adminProfile.copy(
                                firstName = state.firstName,
                                lastName = state.lastName,
                                city = state.city,
                                address = state.address,
                                postalCode = state.postalCode,
                                clinicName = state.clinicName,
                                clinicAddress = state.clinicAddress,
                                clinicCity = state.clinicCity,
                                clinicDescription = state.clinicDescription
                            ))
                            scope.launch {
                                scaffoldState.bottomSheetState.hide()
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = state.checkIfEdited(adminProfile),
                    isLoading = isLoading
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background
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
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(5.dp)
                    .background(Color.Transparent)
                    .verticalScroll(rememberScrollState())
            ) {
                ShowCard(
                    title = "Personal Information",
                    actionIcon = Icons.Default.Edit,
                    onActionClick = {
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
                            ProfileImage(profileUrl = adminProfile.profile,size = 100.dp)
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(text = "${adminProfile.firstName} ${adminProfile.lastName}", fontSize = 20.sp,)
                        }

                        Column {
                            DetailRow(title = "Phone Number", detail = adminProfile.phone)
                            DetailRow(title = "Address", detail = "${adminProfile.address}, ${adminProfile.city}, ${adminProfile.postalCode}")
                            DetailRow(title = "Clinic Name", detail = adminProfile.clinicName)
                            DetailRow(title = "Clinic Address", detail = "${adminProfile.clinicCity}, ${adminProfile.clinicAddress}")
                        }
                    }

                }
                Spacer(modifier = Modifier.size(15.dp))
                ShowCard(
                    title = "Clinic Doctors",
                    actionText = "Show All",
                    onActionClick = {
                        navigateToDoctorList(adminProfile.clinicID)
                    }
                ) {
                    if (doctorList.isEmpty())
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(50.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "These clinic has no doctor", fontSize = 15.sp, textAlign = TextAlign.Center)
                        }
                    else LazyColumn{
                        items(3){
                            val doctor = doctorList[it]
                            DoctorListItem(
                                doctorName = "${doctor.firstName} ${doctor.lastName}",
                                specialty = doctor.specialty,
                                profile = doctor.profileUrl
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.size(15.dp))
                ShowCard(
                    title = "Clinic Patients",
                    actionText = "Show All",
                    onActionClick = {
                        navigateToPatientList(
                            adminProfile.clinicID
                        )
                    }
                ) {
                    if (doctorList.isEmpty())
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(50.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "These clinic has no patient.", fontSize = 15.sp, textAlign = TextAlign.Center)
                        }
                    else LazyColumn{
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



@Preview(showBackground = true, backgroundColor = 0x00B6A2BD)
@Composable
fun AdminDashboardPreview() {
    AdminDashboardContent(
        AdminProfile(
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
        "",
        "",
            ""
        ),
        notificationCount = 2,
        onSaveClick = {},
        navigateToPatientList = {},
        navigateToDoctorList = {}
    ){

    }
}
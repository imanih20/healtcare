package com.example.healthcare.peresentation.profile

import android.text.style.ClickableSpan
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.example.healthcare.common.Prefs
import com.example.healthcare.domain.clinic.Clinic
import com.example.healthcare.peresentation.auth.utils.Role
import com.example.healthcare.peresentation.common.composables.BackLayoutV1
import com.example.healthcare.peresentation.common.composables.MyScaffold
import com.example.healthcare.peresentation.common.utils.BaseViewModel
import com.example.healthcare.peresentation.destinations.AdminDashboardDestination
import com.example.healthcare.peresentation.destinations.DoctorDashboardScreenDestination
import com.example.healthcare.peresentation.destinations.PatientDashboardDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Destination
@Composable
fun CreateProfileScreen(
    navigator: DestinationsNavigator,
    createProfileViewModel: CreateProfileViewModel = koinViewModel(),
    prefs: Prefs = koinInject(),
    role: Role
) {
    val createRequestState = createProfileViewModel.createRequestState.collectAsState()
    val isLoading = createProfileViewModel.isLoading.collectAsState()
    val clinicList = createProfileViewModel.clinicList.collectAsState()

    val adminProfileContentState = rememberAdminProfileState()
    val doctorProfileContentState = rememberDoctorProfileState()
    val patientProfileContentState = rememberPatientProfileState()

    LaunchedEffect(createRequestState.value){
        when(createRequestState.value){
            is BaseViewModel.RequestState.Success -> {
                prefs.writeIsProfileCreated(true)
                when (role){
                    Role.Admin -> navigator.navigate(AdminDashboardDestination,onlyIfResumed = true)
                    Role.Doctor -> navigator.navigate(DoctorDashboardScreenDestination, onlyIfResumed = true)
                    else -> navigator.navigate(PatientDashboardDestination,onlyIfResumed = true)
                }
            }
            is BaseViewModel.RequestState.Error -> {}
            else -> {}
        }
    }

    CreateProfileScreenContent(
        role,
        adminProfileContentState,
        doctorProfileContentState,
        patientProfileContentState,
        isLoading.value,
        clinicList.value,
        onSaveClick = {
            when(role){
                Role.Patient -> {
                    createProfileViewModel.createPatientProfile(
                        patientProfileContentState.medicalHistory,
                        patientProfileContentState.age.toIntOrNull()?:0,
                        patientProfileContentState.height.toDoubleOrNull()?:0.0,
                        patientProfileContentState.weight.toDoubleOrNull()?:0.0
                    )
                }
                Role.Admin -> {
                    createProfileViewModel.createAdminProfile(
                        adminProfileContentState.clinicName,
                        adminProfileContentState.clinicAddress,
                        adminProfileContentState.clinicCity,
                        adminProfileContentState.clinicDescription
                    )
                }
                Role.Doctor -> {
                    createProfileViewModel.createDoctorProfile(
                        doctorProfileContentState.clinicID,
                        doctorProfileContentState.speciality,
                        doctorProfileContentState.degree,
                        doctorProfileContentState.description
                    )
                }

                else -> {}
            }
        }
    )
}

@Composable
fun CreateProfileScreenContent(
    role: Role = Role.Doctor,
    adminProfileState: AdminProfileState = rememberAdminProfileState(),
    doctorProfileState: DoctorProfileState = rememberDoctorProfileState(),
    patientProfileState: PatientProfileState = rememberPatientProfileState(),
    isLoading: Boolean = false,
    clinicList : List<Clinic> = emptyList(),
    onSaveClick: () -> Unit = {}
) {
    MyScaffold {
        BackLayoutV1(
            headerPosition = 0.4f
        ) {
            when(role){
                Role.Patient->{
                    PatientProfile(
                        patientProfileState,
                        isLoading,
                        onSaveClick
                    )
                }
                Role.Admin->{
                    AdminProfile(
                        adminProfileState,
                        isLoading,
                        onSaveClick
                    )
                }
                Role.Doctor->{
                    DoctorProfile(
                        doctorProfileState,
                        isLoading,
                        clinicList,
                        onSaveClick
                    )
                }

                else -> {
                    AdminProfile(
                        adminProfileState,
                        isLoading,
                        onSaveClick
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CreateProfileScreenPreview() {
    CreateProfileScreenContent()
}

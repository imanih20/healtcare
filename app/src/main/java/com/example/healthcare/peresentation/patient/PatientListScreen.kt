package com.example.healthcare.peresentation.patient

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthcare.domain.patient.Patient
import com.example.healthcare.domain.profile.models.PatientProfile
import com.example.healthcare.peresentation.auth.utils.Role
import com.example.healthcare.peresentation.common.composables.BackLayoutV1
import com.example.healthcare.peresentation.common.composables.CPTextField
import com.example.healthcare.peresentation.common.composables.IconBox
import com.example.healthcare.peresentation.common.composables.MyButton
import com.example.healthcare.peresentation.common.composables.MyScaffold
import com.example.healthcare.peresentation.common.composables.MyTextField
import com.example.healthcare.peresentation.common.composables.PatientListItem
import com.example.healthcare.peresentation.common.utils.ADDRESS_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.AGE_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.CLINIC_DESCRIPTION_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.city_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.FIRST_NAME_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.HEIGHT_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.LAST_NAME_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.POSTAL_CODE_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.SAVE_BTN
import com.example.healthcare.peresentation.common.utils.WEIGHT_INPUT_LABEL
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.Plus
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun PatientListScreen(
    navigator: DestinationsNavigator,
    role: Role,
    id: String,
    viewModel: PatientListViewModel = koinViewModel(
        parameters = {
            parametersOf(id,role)
        }
    )
) {
    val patients = viewModel.patients.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()


    PatientListScreenContent(
        patients.value,
        isLoading.value,
        role,
        onBackPressed = {
            navigator.popBackStack()
        },
    )
    ModalBottomSheet(onDismissRequest = { /*TODO*/ }) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientListScreenContent(
    patientList : List<Patient> = emptyList(),
    isLoading: Boolean = false,
    role: Role = Role.Admin,
    state : PatientListState = rememberPatientListState(),
    onBackPressed: () -> Unit = {},
    onSaveClick: (PatientProfile) -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()
    Box{
        BottomSheetScaffold(
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
                    CPTextField(
                        value = state.phone,
                        onValueChange = {state.phone = it},
                        onFullNumber = {
                            state.fullPhoneNumber = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
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
                            if (state.isFilled()){
                                onSaveClick(PatientProfile(
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
                                if(!isLoading) scope.launch {
                                    scaffoldState.bottomSheetState.hide()
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        isLoading = isLoading
                    )
                }
            },
            sheetPeekHeight = 0.dp,
        ) {
            BackLayoutV1(
                headerPosition = 0.08f,
                circleSize = 0.1f,
                circleXPosition = 0.071f,
                headerContent = {
                    CenterAlignedTopAppBar(
                        title = { Text(text = "Patients", color = MaterialTheme.colorScheme.onPrimary) },
                        navigationIcon = {
                            IconButton(onClick = { onBackPressed() }) {
                                IconBox(imageVector = FontAwesomeIcons.Solid.ArrowLeft, tint = MaterialTheme.colorScheme.onPrimary)
                            }
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Color.Transparent
                        )
                    )
                }
            ) {
                Box(Modifier.fillMaxSize()){
                    if(patientList.isEmpty()){
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "No Patients Available")
                        }
                    } else {
                        LazyColumn{
                            items(patientList){
                                PatientListItem(patient = it)
                            }
                        }
                    }
                    if(role == Role.Admin){
                        FloatingActionButton(
                            onClick = {
                                scope.launch {
                                    scaffoldState.bottomSheetState.expand()
                                }
                            },
                            modifier = Modifier.align(Alignment.BottomEnd)
                        ) {
                            IconBox(imageVector = FontAwesomeIcons.Solid.Plus)
                        }
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun PatientListScreenPreview() {
    PatientListScreenContent()
}
package com.example.healthcare.peresentation.doctor.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.healthcare.domain.doctor.Doctor
import com.example.healthcare.peresentation.auth.utils.Role
import com.example.healthcare.peresentation.common.composables.BackLayoutV1
import com.example.healthcare.peresentation.common.composables.IconBox
import com.example.healthcare.peresentation.common.composables.MyScaffold
import com.example.healthcare.peresentation.common.composables.DoctorListItem
import com.example.healthcare.peresentation.doctor.viewModels.DoctorListViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.Plus
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Destination
@Composable
fun DoctorListScreen(
    navigator: DestinationsNavigator,
    clinicId: String,
    viewModel: DoctorListViewModel = koinViewModel(
        parameters = { parametersOf(clinicId) }
    )
) {
    val doctors = viewModel.doctors.collectAsState()

    DoctorListScreenContent(
        doctors.value,
        onBackPressed = {
            navigator.popBackStack()
        },
        onAddPressed = {

        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorListScreenContent(
    doctorList : List<Doctor> = emptyList(),
    onBackPressed: ()->Unit = {},
    onAddPressed: ()->Unit = {}
) {
    MyScaffold(
        fab = {
        }
    ) {
        BackLayoutV1(
            headerPosition = 0.08f,
            circleSize = 0.1f,
            circleXPosition = 0.071f,
            headerContent = {
                CenterAlignedTopAppBar(
                title = { Text(text = "Doctors", color = MaterialTheme.colorScheme.onPrimary) },
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
            if(doctorList.isEmpty()){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "No Doctors Available")
                }
            } else {
                LazyColumn{
                    items(doctorList){
                        DoctorListItem(doctorName = "${it.firstName} ${it.lastName}", specialty = it.specialty, profile = it.profileUrl)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DoctorListScreenPreview() {
    DoctorListScreenContent()
}
package com.example.healthcare.peresentation.prescription

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
import com.example.healthcare.domain.prescription.Prescription
import com.example.healthcare.peresentation.common.composables.BackLayoutV1
import com.example.healthcare.peresentation.common.composables.IconBox
import com.example.healthcare.peresentation.common.composables.MyScaffold
import com.example.healthcare.peresentation.common.composables.PrescriptionListItem
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
fun PrescriptionListScreen(
    navigator: DestinationsNavigator,
    patientId: String,
    viewModel: PrescriptionListViewModel = koinViewModel(
        parameters = {
            parametersOf(patientId)
        }
    )
) {
    val prescriptions = viewModel.prescription.collectAsState()
    PrescriptionListScreenContent(
        prescriptions.value,
        onBackPressed = {
            navigator.popBackStack()
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrescriptionListScreenContent(
    prescriptionList : List<Prescription> = emptyList(),
    onBackPressed: ()-> Unit = {}
) {
    MyScaffold {
        BackLayoutV1(
            headerPosition = 0.08f,
            circleSize = 0.1f,
            circleXPosition = 0.071f,
            headerContent = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Prescriptions", color = MaterialTheme.colorScheme.onPrimary) },
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
        ){
            if(prescriptionList.isEmpty()){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "You don't have any prescriptions yet")
                }
            } else {
                LazyColumn{
                    items(prescriptionList){
                        PrescriptionListItem(prescription= it)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PrescriptionListScreenPreview() {
    PrescriptionListScreenContent()
}
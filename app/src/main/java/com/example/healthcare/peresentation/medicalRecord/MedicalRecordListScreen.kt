package com.example.healthcare.peresentation.medicalRecord

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.healthcare.domain.medicalRecord.MedicalRecord
import com.example.healthcare.peresentation.common.composables.BackLayoutV1
import com.example.healthcare.peresentation.common.composables.IconBox
import com.example.healthcare.peresentation.common.composables.MyScaffold
import com.example.healthcare.peresentation.common.composables.RecordListItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.TimesCircle
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.Cross
import compose.icons.fontawesomeicons.solid.Plus
import compose.icons.fontawesomeicons.solid.RemoveFormat
import compose.icons.fontawesomeicons.solid.Times
import compose.icons.fontawesomeicons.solid.Trash
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


@Destination
@Composable
fun MedicalRecordList(
    navigator: DestinationsNavigator = EmptyDestinationsNavigator,
    patientId: String,
    viewModel: MedicalRecordViewModel = koinViewModel(
        parameters = { parametersOf(patientId) }
    ),
) {
    val recordList = viewModel.records.collectAsState()
    val state = rememberMedicalRecordListState(recordList.value)
    MedicalRecordListContent(
        recordList.value,
        state,
        onBackPressed = {
            navigator.popBackStack()
        },
        onDelete = {
            viewModel.deleteRecords(state.getSelected())
        },
        onDownloadPressed = {

        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MedicalRecordListContent(
    recordList: List<MedicalRecord> = emptyList(),
    state: MedicalRecordListState ,
    onBackPressed: () -> Unit = {},
    onDelete: () -> Unit = {},
    onDownloadPressed: (MedicalRecord) -> Unit = {}
) {
    MyScaffold(
        fab = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                IconBox(imageVector = FontAwesomeIcons.Solid.Plus, tint = MaterialTheme.colorScheme.primary)
            }
        }
    ) {
        BackLayoutV1(
            headerPosition = 0.08f,
            circleSize = 0.1f,
            circleXPosition = 0.071f,
            headerContent = {
                CenterAlignedTopAppBar(
                    title = {
                        if (!state.isAnySelected()) Text(text = "Medical Records",color = MaterialTheme.colorScheme.onPrimary)
                    },
                    navigationIcon = {
                        if (!state.isAnySelected()) IconButton(onClick = { onBackPressed() }) {
                            IconBox(imageVector = FontAwesomeIcons.Solid.ArrowLeft, tint = MaterialTheme.colorScheme.onPrimary)
                        }
                        else Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { state.unSelectAll() }) {
                                IconBox(imageVector = Icons.Default.Close, tint = MaterialTheme.colorScheme.onPrimary)
                            }
                            Spacer(modifier = Modifier.size(5.dp))
                            Text(
                                text = "${state.countSelected()} selected",
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    actions = {
                        if (state.isAnySelected()){
                            IconButton(onClick = { onDelete() }) {
                                IconBox(imageVector = FontAwesomeIcons.Solid.Trash, tint = MaterialTheme.colorScheme.onPrimary)
                            }
                        }
                    }
                )
            }
        ) {
            if(recordList.isEmpty()){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "No Record Available Available")
                }
            } else {
                LazyColumn{
                    items(recordList){
                        RecordListItem(
                            Modifier.combinedClickable(onClick = {}, onLongClick = {
                                state.toggle(it.id)
                            }),
                            medicalRecord = it,
                            isSelected = state.selectList[it.id]?: false
                        ) {
                            onDownloadPressed(it)
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
fun MedicalRecordListPreview() {
    MedicalRecordListContent(state = rememberMedicalRecordListState(list = emptyList()))
}
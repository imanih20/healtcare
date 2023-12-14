package com.example.healthcare.peresentation.doctor.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.healthcare.peresentation.common.composables.BackLayoutV1
import com.example.healthcare.peresentation.common.composables.IconBox
import com.example.healthcare.peresentation.common.composables.MyScaffold
import com.example.healthcare.peresentation.common.composables.ProfileImage
import com.example.healthcare.peresentation.doctor.viewModels.DoctorScreenState
import com.example.healthcare.peresentation.doctor.viewModels.rememberDoctorScreenState
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft

@Composable
fun DoctorScreen() {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorScreenContent(state : DoctorScreenState = rememberDoctorScreenState()) {
    MyScaffold {
        BackLayoutV1(
            headerPosition = 0.35f,
            headerArrangement = Arrangement.Top,
            circleYPosition = 0.85f,
            headerContent = {
                Column(
                    Modifier.fillMaxHeight(0.35f),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(text = "Doctor Profile",color = MaterialTheme.colorScheme.onPrimary)
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Color.Transparent
                        ),
                        navigationIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                IconBox(imageVector = FontAwesomeIcons.Solid.ArrowLeft)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    ProfileImage(profileUrl = "",size = 100.dp)
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(text = "John Doe", color = MaterialTheme.colorScheme.onPrimary, fontSize = 16.sp)
                    Text(text = "General Surgeon", color = MaterialTheme.colorScheme.onPrimary)
                    Spacer(modifier = Modifier.size(10.dp))
                }
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(60.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .background(Color.Transparent),
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        TextButton(
                            onClick = {
                                state.selectedTab = 0
                            },
                            modifier = Modifier
                                .padding(5.dp)
                                .weight(1f),
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = if(state.selectedTab == 0)
                                    MaterialTheme.colorScheme.background else Color.Transparent
                            ),
                            shape = RoundedCornerShape(60.dp),
                        ) {
                            Text(text = "Schedule")
                        }
                        TextButton(
                            onClick = {
                                state.selectedTab = 1
                            },
                            modifier = Modifier
                                .padding(5.dp)
                                .weight(1f),
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = if(state.selectedTab == 1)
                                    MaterialTheme.colorScheme.background else Color.Transparent
                            ),
                            shape = RoundedCornerShape(60.dp),
                        ) {
                            Text(text = "About Doctor")
                        }
                    }

                }
                Spacer(modifier = Modifier.size(10.dp))
                if(state.selectedTab == 0) {
                    ScheduleTab()
                } else {
                    AboutDoctorTab()
                }
            }
        }
    }
}

@Composable
fun AboutDoctorTab() {
    TODO("Not yet implemented")
}

@Composable
fun ScheduleTab() {
    Column {

    }
}

@Preview
@Composable
fun DoctorScreenPreview() {
    DoctorScreenContent()
}
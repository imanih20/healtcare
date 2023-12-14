package com.example.healthcare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.window.Dialog
import com.example.healthcare.common.Prefs
import com.example.healthcare.common.checkAndRequestInternetPermission
import com.example.healthcare.peresentation.NavGraphs
import com.example.healthcare.peresentation.destinations.AdminDashboardDestination
import com.example.healthcare.peresentation.destinations.CreateProfileScreenDestination
import com.example.healthcare.peresentation.destinations.DoctorDashboardScreenDestination
import com.example.healthcare.peresentation.destinations.IntroScreenDestination
import com.example.healthcare.peresentation.destinations.LoginScreenDestination
import com.example.healthcare.peresentation.destinations.PatientDashboardDestination
import com.example.healthcare.peresentation.destinations.PatientListScreenDestination
import com.example.healthcare.ui.theme.HealthCareTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var showNoticeDialog by remember {
                mutableStateOf(false)
            }

            val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()){
                showNoticeDialog = !it.values.reduce{acc,next -> acc && next}
            }
            checkAndRequestInternetPermission(this@MainActivity,launcher)
            val prefs : Prefs = koinInject()
            val startRoute = if(!prefs.getIsFirstLaunched())
                IntroScreenDestination
            else if (!prefs.getIsLogIn())
                LoginScreenDestination
            else if (!prefs.getIsProfileCreate())
                CreateProfileScreenDestination
            else {
                when(prefs.getRole()?.lowercase()){
                    "admin"-> AdminDashboardDestination
                    "doctor"-> DoctorDashboardScreenDestination
                    else -> PatientDashboardDestination
                }
            }
            HealthCareTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DestinationsNavHost(navGraph = NavGraphs.root, startRoute = startRoute)
                }
            }
            if (showNoticeDialog){
                AlertDialog(
                    onDismissRequest = {
                        this.finish()
                    },
                    confirmButton = {
                        Text(text = "Give Access")
                    },
                    dismissButton = {
                        Text(text = "Quit")
                    },
                    text = {
                        Text(text = "We need to have permission to access you Internet for this app")
                    }
                )
            }else {
                DestinationsNavHost(navGraph = NavGraphs.root, startRoute = startRoute)
            }
        }
    }
}

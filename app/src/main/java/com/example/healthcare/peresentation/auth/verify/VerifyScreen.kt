package com.example.healthcare.peresentation.auth.verify

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthcare.common.Prefs
import com.example.healthcare.peresentation.common.utils.OTP_NOTICE
import com.example.healthcare.peresentation.auth.utils.Role
import com.example.healthcare.peresentation.common.utils.VERIFY_BTN
import com.example.healthcare.peresentation.common.composables.BackLayoutV1
import com.example.healthcare.peresentation.common.composables.IconBox
import com.example.healthcare.peresentation.common.composables.MyButton
import com.example.healthcare.peresentation.common.composables.MyScaffold
import com.example.healthcare.peresentation.common.composables.OtpTextField
import com.example.healthcare.peresentation.common.utils.BaseViewModel
import com.example.healthcare.peresentation.destinations.AdminDashboardDestination
import com.example.healthcare.peresentation.destinations.CreateProfileScreenDestination
import com.example.healthcare.peresentation.destinations.DoctorDashboardScreenDestination
import com.example.healthcare.peresentation.destinations.PatientDashboardDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Destination
@Composable
fun VerifyScreen(
    navigator: DestinationsNavigator,
    viewModel: VerifyViewModel = koinViewModel(),
    firstName: String = "",
    lastName: String = "",
    address: String = "",
    city: String = "",
    postalCode: String = "",
    role: Role? = null
) {
    val verifyRegisterRequestState = viewModel.verifyRegisterState.collectAsState()
    val verifyLoginRequestState = viewModel.verifyLoginState.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val contentState = rememberVerifyState()

    LaunchedEffect(verifyLoginRequestState.value){
        when(verifyLoginRequestState.value){
            is BaseViewModel.RequestState.Success -> {
                when (role){
                    Role.Admin -> navigator.navigate(AdminDashboardDestination, onlyIfResumed = true)
                    Role.Doctor -> navigator.navigate(DoctorDashboardScreenDestination,onlyIfResumed = true)
                    else -> navigator.navigate(PatientDashboardDestination,onlyIfResumed = true)
                }
            }
            is BaseViewModel.RequestState.Error -> {}
            else -> {}
        }
    }

    LaunchedEffect(verifyRegisterRequestState.value) {
        when(verifyRegisterRequestState.value){
            is BaseViewModel.RequestState.Success -> {
                navigator.navigate(CreateProfileScreenDestination(role?:Role.Patient))
            }
            is BaseViewModel.RequestState.Error -> {}
            else -> {}
        }
    }

    VerifyScreenContent(
        contentState,
        isLoading.value,
        onVerifyClick = {
            if(role == null){
                viewModel.verifyUserLogin(contentState.otpCode)
            } else {
                viewModel.verifyUserRegister(
                    firstName,
                    lastName,
                    city,
                    address,
                    postalCode,
                    contentState.otpCode
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerifyScreenContent(
    state : VerifyScreenState = rememberVerifyState(),
    isLoading: Boolean = true,
    onVerifyClick: ()->Unit = {}
) {
    MyScaffold {
        BackLayoutV1(
            circleYPosition = 0.6f,
            headerArrangement = Arrangement.Top,
            headerContent = {

            }
        ) {
            Text(
                text = OTP_NOTICE,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.size(30.dp))
            OtpTextField(otpText = state.otpCode, onOtpTextChange = {otp,ok->
                state.otpCode = otp
            })
            Spacer(modifier = Modifier.size(30.dp))
            MyButton(
                title = VERIFY_BTN,
                onClick = onVerifyClick,
                modifier = Modifier.fillMaxWidth(),
                isLoading = isLoading
            )
            Spacer(modifier = Modifier.size(15.dp))
//            MyButton(
//                title = "",
//                onClick = { },
//                modifier = Modifier.fillMaxWidth(),
//                isElevated = true,
//                enabled = false
//            )
        }
    }
}

@Preview
@Composable
fun VerifyScreenPreview() {
    VerifyScreenContent()
}
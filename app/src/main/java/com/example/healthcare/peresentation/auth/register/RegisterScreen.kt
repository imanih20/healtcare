package com.example.healthcare.peresentation.auth.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.healthcare.peresentation.common.utils.ADDRESS_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.ADMIN_TXT
import com.example.healthcare.peresentation.common.utils.city_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.DOCTOR_TXT
import com.example.healthcare.peresentation.common.utils.FIRST_NAME_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.LAST_NAME_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.LOGIN_BTN
import com.example.healthcare.peresentation.common.utils.LOGIN_NOTICE
import com.example.healthcare.peresentation.common.utils.PATIENT_TXT
import com.example.healthcare.peresentation.common.utils.POSTAL_CODE_INPUT_LABEL
import com.example.healthcare.peresentation.common.utils.REGISTER_AS_TEXT
import com.example.healthcare.peresentation.auth.utils.Role
import com.example.healthcare.peresentation.common.utils.SIGN_BTN
import com.example.healthcare.peresentation.common.composables.BackLayoutV1
import com.example.healthcare.peresentation.common.composables.CPTextField
import com.example.healthcare.peresentation.common.composables.MyButton
import com.example.healthcare.peresentation.common.composables.MyRadioButton
import com.example.healthcare.peresentation.common.composables.MyScaffold
import com.example.healthcare.peresentation.common.composables.MyTextField
import com.example.healthcare.peresentation.common.utils.BaseViewModel
import com.example.healthcare.peresentation.destinations.LoginScreenDestination
import com.example.healthcare.peresentation.destinations.VerifyScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun RegisterScreen(navigator: DestinationsNavigator, viewModel: RegisterViewModel = koinViewModel()) {
    val requestState = viewModel.registerRequestState.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val contentState = rememberSignScreenState()
    LaunchedEffect(requestState.value){
        when(requestState.value){
            is BaseViewModel.RequestState.Success -> {
                navigator.navigate(
                    VerifyScreenDestination(
                        firstName = contentState.firstName,
                        lastName = contentState.lastName,
                        address = contentState.address,
                        city = contentState.city,
                        postalCode = contentState.postalCode,
                        role = contentState.selectedRole
                    )
                )
            }
            is BaseViewModel.RequestState.Error -> {}
            else -> {}
        }
    }
    RegisterScreenContent(
        contentState,
        isLoading.value,
        onRegisterClicked = {
            if (contentState.isFieldsFull()){
                viewModel.registerUser(
                    contentState.fullPhoneNumber,
                    contentState.selectedRole.name
                )
            }
        },
        onNavigateToLogin = {
            navigator.navigate(LoginScreenDestination, onlyIfResumed = true)
        }
    )
}

@Composable
fun RegisterScreenContent(
    state: SignScreenState = rememberSignScreenState(),
    isLoading: Boolean = false,
    onRegisterClicked: () -> Unit = {},
    onNavigateToLogin: () -> Unit = {}
) {
    val headerPosition = 0.27f
    MyScaffold {
        BackLayoutV1(
            headerPosition = headerPosition,
            circleYPosition = 0.9f
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
//            MyTextField(
//                value = state.phone,
//                onValueChange = {
//                    state.phone = it
//                },
//                Modifier.fillMaxWidth(),
//                label = PHONE_INPUT_LABEL
//            )
            CPTextField(
                value = state.phone,
                onValueChange = {
                    state.phone = it
                },
                onFullNumber = {
                    state.fullPhoneNumber = it
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(15.dp))
            Text(
                text = REGISTER_AS_TEXT,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                MyRadioButton(
                    title = ADMIN_TXT,
                    selected = state.selectedRole == Role.Admin
                ) {
                    state.selectedRole = Role.Admin
                }
                Spacer(modifier = Modifier.size(10.dp))
                MyRadioButton(
                    title = DOCTOR_TXT,
                    selected = state.selectedRole == Role.Doctor
                ) {
                    state.selectedRole = Role.Doctor
                }
                Spacer(modifier = Modifier.size(10.dp))
                MyRadioButton(
                    title = PATIENT_TXT,
                    selected = state.selectedRole == Role.Patient
                ) {
                    state.selectedRole = Role.Patient
                }
            }
            Spacer(modifier = Modifier.size(30.dp))
            MyButton(
                title = SIGN_BTN,
                onClick = onRegisterClicked,
                Modifier.fillMaxWidth(),
                isLoading = isLoading
            )
            Spacer(modifier = Modifier.size(30.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = LOGIN_NOTICE)
                Text(
                    text = LOGIN_BTN,
                    modifier = Modifier.clickable {
                        onNavigateToLogin()
                    },
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreenContent()
}
package com.example.healthcare.peresentation.auth.login

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthcare.peresentation.common.utils.LOGIN_BTN
import com.example.healthcare.peresentation.common.utils.SIGN_BTN
import com.example.healthcare.peresentation.common.utils.SIGN_IN_NOTICE
import com.example.healthcare.peresentation.common.utils.SIGN_UP_NOTICE
import com.example.healthcare.peresentation.common.utils.WELCOME_TXT
import com.example.healthcare.peresentation.common.composables.BackLayoutV1
import com.example.healthcare.peresentation.common.composables.CPTextField
import com.example.healthcare.peresentation.common.composables.MyButton
import com.example.healthcare.peresentation.common.composables.MyScaffold
import com.example.healthcare.peresentation.common.utils.BaseViewModel
import com.example.healthcare.peresentation.destinations.RegisterScreenDestination
import com.example.healthcare.peresentation.destinations.VerifyScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    viewModel: LoginViewModel = koinViewModel()
) {
    val requestState = viewModel.loginRequestState.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val contentState = rememberLoginScreenState()

    LaunchedEffect(requestState.value){
        when(requestState.value){
            is BaseViewModel.RequestState.Success ->{
                navigator.navigate(VerifyScreenDestination(), onlyIfResumed = true)
            }
            is BaseViewModel.RequestState.Error -> {

            }
            else ->{}
        }
    }

    LoginScreenContent(
        state = contentState,
        isLoading = isLoading.value,
        onLoginClick = {
            if (contentState.fullPhoneNumber.isNotEmpty()) {
                viewModel.loginUser(contentState.fullPhoneNumber)
            }
        },
        onNavigateToSignUp = {
            navigator.navigate(RegisterScreenDestination)
        }
    )
}

@Composable
fun LoginScreenContent(
    state: LoginScreenState = rememberLoginScreenState(),
    isLoading: Boolean = false,
    onLoginClick: () -> Unit = {},
    onNavigateToSignUp: () -> Unit = {}
) {
    val headerPosition = 0.5f
    MyScaffold {
        BackLayoutV1(
            headerPosition=headerPosition,
            circleYPosition = 0.4f
        ) {
            Text(text = WELCOME_TXT, style = MaterialTheme.typography.displaySmall)
            Spacer(modifier = Modifier.size(5.dp))
            Text(text = SIGN_IN_NOTICE, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.size(20.dp))
//            MyTextField(
//                value = state.phoneNumber,
//                onValueChange = {
//                    state.phoneNumber = it
//                },
//                label = PHONE_INPUT_LABEL,
//                modifier = Modifier.fillMaxWidth()
//            )
            CPTextField(
                value = state.phoneNumber,
                onValueChange = {
                    state.phoneNumber = it
                },
                onFullNumber = {
                    state.fullPhoneNumber = it
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(30.dp))
            MyButton(
                title = LOGIN_BTN,
                onClick = { onLoginClick() },
                modifier = Modifier.fillMaxWidth(),
                isLoading = isLoading
            )
            Spacer(modifier = Modifier.size(30.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = SIGN_UP_NOTICE)
                Text(
                    text = SIGN_BTN,
                    modifier = Modifier.clickable {
                        onNavigateToSignUp()
                    },
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreenContent()
}
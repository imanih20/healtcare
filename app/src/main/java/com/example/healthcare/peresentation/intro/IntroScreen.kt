package com.example.healthcare.peresentation.intro

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.healthcare.R
import com.example.healthcare.common.Prefs
import com.example.healthcare.peresentation.common.composables.BackLayoutV2
import com.example.healthcare.peresentation.common.composables.MyScaffold
import com.example.healthcare.peresentation.destinations.LoginScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.ArrowRight
import org.koin.compose.koinInject

@RootNavGraph(start = true)
@Destination
@Composable
fun IntroScreen(navigator: DestinationsNavigator,prefs: Prefs = koinInject()) {
    val state = rememberIntroScreenState()

    LaunchedEffect(state.page){
        when(state.page){
            0->{
                state.introText = "Welcome to HealthAssist. \n Your personal health assistant."
                state.prevVisisble = false
                state.verctorId = R.drawable.intro3
            }
            1->{
                state.introText = "Quick and easy access to your health."
                state.prevVisisble = true
                state.verctorId = R.drawable.intro2
            }
            2->{
                state.introText = "HealthAssist puts healthcare to your hands."
                state.prevVisisble = true
                state.verctorId = R.drawable.intro1
            }
        }
    }
    LaunchedEffect(key1 = state.isGoToNextScreen){
        if(state.isGoToNextScreen){
            prefs.writeIsFirstLaunched(true)
            navigator.navigate(LoginScreenDestination)
        }
    }
    MyScaffold {
        BackLayoutV2(
            headerPosition = 0.68f,
            circleSize = 0.6f,
            headerArrangement = Arrangement.Bottom,
            headerContent = {
                Box{
                    Image(
                        painter = painterResource(id = state.verctorId),
                        contentDescription = "")
                }
            }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = state.introText, fontSize = 30.sp, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.size(10.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (state.prevVisisble) NavigateButton(icon = FontAwesomeIcons.Solid.ArrowLeft) {
                        state.prevPage()
                    }
                    else Spacer(modifier = Modifier.size(50.dp))
                    Row {
                        Spacer(
                            modifier = Modifier
                                .width(
                                    if (state.page == 0) 30.dp
                                    else 6.dp
                                )
                                .height(6.dp)
                                .clip(CircleShape)
                                .background(Color.Gray)
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Spacer(
                            modifier = Modifier
                                .width(
                                    if (state.page == 1) 30.dp
                                    else 6.dp
                                )
                                .height(6.dp)
                                .clip(CircleShape)
                                .background(Color.Gray)
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Spacer(
                            modifier = Modifier
                                .width(
                                    if (state.page == 2) 30.dp
                                    else 6.dp
                                )
                                .height(6.dp)
                                .clip(CircleShape)
                                .background(Color.Gray)
                        )

                    }
                    NavigateButton(icon = FontAwesomeIcons.Solid.ArrowRight) {
                        state.nextPage()
                    }
                }
            }
        }
    }
}

@Composable
fun NavigateButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .border(BorderStroke(2.dp, MaterialTheme.colorScheme.primary), shape = CircleShape)
            .padding(5.dp)
            .clip(CircleShape)
    ){
        FilledIconButton(
            onClick =  onClick,
            modifier = Modifier.size(50.dp)
        ) {
            Box(modifier = Modifier.padding(10.dp)){
                Icon(imageVector = icon, contentDescription = "")
            }
        }
    }
    
}

@Preview(apiLevel = 33)
@Composable
fun IntroScreenPreview() {
    IntroScreen(EmptyDestinationsNavigator)
}
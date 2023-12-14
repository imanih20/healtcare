package com.example.healthcare.peresentation.intro

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.healthcare.R

@Composable
fun rememberIntroScreenState() : IntroScreenState {
    return remember {
        IntroScreenState()
    }
}


class IntroScreenState {
    var page by mutableIntStateOf(0)
    var introText by mutableStateOf("")
    var prevVisisble by mutableStateOf(false)
    var isGoToNextScreen by mutableStateOf(false)
    var verctorId by mutableIntStateOf(R.drawable.intro3)
    
    fun nextPage(){
        if (page == 2) isGoToNextScreen = true
        else {
            isGoToNextScreen = false
            page++
        }
    }

    fun prevPage(){
        page--
    }
}
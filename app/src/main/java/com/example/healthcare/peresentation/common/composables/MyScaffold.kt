package com.example.healthcare.peresentation.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun MyScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    fab: @Composable ()-> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier,
        topBar = topBar,
        floatingActionButton = fab,
    ) {
        Box(modifier = Modifier.padding(it)){
            content()
        }
    }
}

@Preview
@Composable
fun MyScaffoldPreview() {
    MyScaffold {

    }
}
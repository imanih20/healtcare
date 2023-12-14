package com.example.healthcare.peresentation.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun BackLayoutV1(
    headerPosition: Float = 0.5f,
    circleYPosition: Float = 0.5f,
    circleXPosition: Float = 0.5f,
    circleSize: Float = 0.5f,
    contentPadding: Dp = 15.dp,
    headerArrangement: Arrangement.Vertical = Arrangement.Center,
    headerContent: (@Composable ()->Unit)? = null,
    content: @Composable (ColumnScope.()->Unit)
) {
    ConstraintLayout(modifier = Modifier
        .background(MaterialTheme.colorScheme.primary)
    ) {
        val centerGuideLine = createGuidelineFromTop(headerPosition)
        val box = createRef()
        val cont = createRef()
        BoxBackground(
            modifier = Modifier.constrainAs(box){
                bottom.linkTo(centerGuideLine)
            },
            yPosition = circleYPosition,
            xPosition = circleXPosition,
            circleSize = circleSize,
            contentArrangement = headerArrangement
        ) {
            if (headerContent != null) {
                headerContent()
            }
        }
        val cornerSize = 60.dp
        Card(
            modifier = Modifier
                .constrainAs(cont) {
                    top.linkTo(box.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            shape = RoundedCornerShape(topStart = cornerSize, topEnd = cornerSize),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 30.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ){
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1-headerPosition)
                    .padding(contentPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
            }
        }
    }
}
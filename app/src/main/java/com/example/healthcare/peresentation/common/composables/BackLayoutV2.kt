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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun BackLayoutV2(
    headerPosition: Float = 0.5f,
    circleYPosition: Float = 0.5f,
    circleXPosition: Float = 0.5f,
    circleSize: Float = 0.5f,
    contentPadding: Dp = 15.dp,
    headerArrangement: Arrangement.Vertical = Arrangement.Center,
    headerContent: (@Composable ()->Unit)? = null,
    content: @Composable (ColumnScope.()->Unit)
) {
    ConstraintLayout {
        val centerGuideLine = createGuidelineFromTop(headerPosition)
        val box = createRef()
        val cont = createRef()
        val cornerSize = 60.dp
        BoxBackground(
            modifier = Modifier
                .fillMaxHeight(headerPosition)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clip(RoundedCornerShape(bottomStart = cornerSize, bottomEnd = cornerSize))
                .shadow(30.dp, RoundedCornerShape(bottomEnd = cornerSize, bottomStart = cornerSize))
                .constrainAs(box) {
                    top.linkTo(parent.top)
                    bottom.linkTo(centerGuideLine)
                }
            ,
            xPosition = circleXPosition,
            yPosition = circleYPosition,
            circleSize = circleSize,
            contentArrangement = headerArrangement
        ) {
            if (headerContent != null) {
                headerContent()
            }
        }
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .constrainAs(cont) {
                    top.linkTo(centerGuideLine)
                    bottom.linkTo(parent.bottom)
                }
        ){
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1 - headerPosition)
                    .padding(contentPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
            }
        }
    }
}
package com.example.healthcare.peresentation.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.toSize

@Composable
fun BoxBackground(
    modifier: Modifier = Modifier,
    xPosition: Float = 0.5f,
    yPosition: Float = 0.5f,
    circleSize: Float = 0.5f,
    contentArrangement: Arrangement.Vertical = Arrangement.Center,
    content: @Composable ()->Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .drawBehind {
                val cirSize = size.width * circleSize
                val xOffset = size.width * xPosition
                val yOffset = size.height * yPosition
                drawCircle(
                    Color(0x62, 0x61, 0xff),
                    radius = cirSize,
                    center = Offset(xOffset, yOffset)
                )
                drawCircle(
                    Color(0x82, 0x83, 0xff),
                    radius = (cirSize / 1.7).toFloat(),
                    center = Offset(xOffset, yOffset)
                )
            },
        verticalArrangement = contentArrangement,
    ) {
        content()
    }

}

@Preview
@Composable
fun BoxBackPreview() {
    BoxBackground {

    }
}
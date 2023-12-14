package com.example.healthcare.peresentation.common.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.healthcare.peresentation.common.composables.IconBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BadgeIcon(icon: ImageVector,number: Int = 0,onClick: ()->Unit) {
    BadgedBox(badge = {
        if (number > 0) Badge {
            Text(text = number.toString() )
        }
    }, modifier = Modifier.padding(10.dp).clickable { onClick() }) {
        IconBox(imageVector = icon, tint = MaterialTheme.colorScheme.onPrimary)
    }
}
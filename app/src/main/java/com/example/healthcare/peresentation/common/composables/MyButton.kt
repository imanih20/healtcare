package com.example.healthcare.peresentation.common.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyButton(
    title: String,
    onClick: ()->Unit,
    modifier: Modifier = Modifier,
    isElevated: Boolean = false,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    val text = @Composable {
        if(isLoading) CircularProgressIndicator()
        else Text(text = title,Modifier.padding(9.dp), fontSize = 17.sp)
    }
    if (isElevated)
        ElevatedButton(onClick = onClick, modifier = modifier,enabled = enabled && !isLoading) {
            text()
        }
    else
        Button(modifier = modifier, onClick = onClick, enabled = enabled && !isLoading) {
            text()
        }
}
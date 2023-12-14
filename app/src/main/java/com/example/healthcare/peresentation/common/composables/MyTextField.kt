package com.example.healthcare.peresentation.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyTextField(
    value: String,
    onValueChange: (String)->Unit,
    modifier : Modifier = Modifier,
    label: String,
    singleLine : Boolean = true,
    maxLines : Int = 1,
    keyboardOptions : KeyboardOptions = KeyboardOptions.Default,
    leading: @Composable (()->Unit)? = null
) {
    OutlinedTextField(
        value = value ,
        onValueChange = onValueChange,
        modifier = modifier,
        shape = RoundedCornerShape(30.dp),
        label = {
            Text(text = label)
        },
        singleLine = singleLine,
        maxLines = maxLines,
        leadingIcon = leading,
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            disabledContainerColor = MaterialTheme.colorScheme.background,
            errorContainerColor = MaterialTheme.colorScheme.background,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedIndicatorColor = Color.Transparent
        )

    )
}
package com.example.healthcare.peresentation.common.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailRow(
    modifier: Modifier = Modifier,
    title: String,
    detail: String
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 15.dp)) {
        Text(text = title, fontSize = 17.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.size(5.dp))
        Text(text = detail, fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)
    }
}
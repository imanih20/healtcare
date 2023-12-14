package com.example.healthcare.peresentation.common.composables

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.healthcare.R

@Composable
fun ProfileImage(
    profileUrl: String,
    size: Dp = 50.dp
) {
    AsyncImage(
        model = profileUrl,
        contentDescription = "",
        modifier = Modifier.size(size),
        placeholder = painterResource(id = R.drawable.profile),
        error = painterResource(id = R.drawable.profile),
        contentScale = ContentScale.Crop
    )
}
package com.example.healthcare.peresentation.common.composables

import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DoctorListItem(
    doctorName: String,
    specialty: String,
    profile: String
){
    Card {
        ListItem(
            headlineContent = {
                Text(text = doctorName)
            },
            leadingContent = {
                ProfileImage(profileUrl = profile)
            },
            supportingContent = {
                Text(text = specialty)
            }
        )
    }
}


@Preview
@Composable
fun DoctorListItemPreview() {
    DoctorListItem(
        doctorName = "John Doe",
        "General",
        ""
    )
}

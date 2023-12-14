package com.example.healthcare.peresentation.common.composables

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.healthcare.domain.medicalRecord.MedicalRecord
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Download

@Composable
fun RecordListItem(
    modifier : Modifier = Modifier,
    medicalRecord: MedicalRecord,
    isSelected: Boolean = false,
    onDownload:() -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if(isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.background
        )
    ) {

        ListItem(
            headlineContent = {
                Text(text = medicalRecord.recordType)
            },
            supportingContent = {
                Text(text = medicalRecord.notes)
            },
            trailingContent = {
                IconButton(onClick = onDownload) {
                    IconBox(imageVector = FontAwesomeIcons.Solid.Download, tint = MaterialTheme.colorScheme.primary)
                }
            },
            overlineContent = {
                Text(text = medicalRecord.date)
            },
            colors = ListItemDefaults.colors(
                containerColor = Color.Transparent
            )
        )
    }
}

@Preview
@Composable
fun RecordListItemPreview() {
    RecordListItem(medicalRecord = MedicalRecord(
        "",
        "",
        "Blood Test",
        "10-10-2023",
        "",
        "blood test"
    )){}
}
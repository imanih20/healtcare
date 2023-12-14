package com.example.healthcare.peresentation.common.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.healthcare.domain.notification.Notification
import com.example.healthcare.peresentation.common.composables.IconBox
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Trash

@Composable
fun NotificationListItem(
    notification: Notification,
    onDelete: () -> Unit
) {

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        ListItem(
            headlineContent = {
                Text(
                    text = notification.title
                )
            },
            supportingContent = {
                Text(
                    text = notification.body
                )
            },
            trailingContent = {
                IconButton(onClick = onDelete) {
                    IconBox(imageVector = Icons.Default.Delete,tint = MaterialTheme.colorScheme.error)
                }
            },
            colors = ListItemDefaults.colors(
                containerColor = Color.Transparent
            )
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (!notification.isRead) Text(text = "new", fontSize = 16.sp, color = Color.Green)
            else Spacer(modifier = Modifier.size(10.dp))
            Text(text = notification.date, style = MaterialTheme.typography.labelMedium)
        }
    }
}
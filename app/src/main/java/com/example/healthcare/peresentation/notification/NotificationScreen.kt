package com.example.healthcare.peresentation.notification

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthcare.domain.notification.Notification
import com.example.healthcare.peresentation.common.composables.BoxBackPreview
import com.example.healthcare.peresentation.common.composables.BoxBackground
import com.example.healthcare.peresentation.common.composables.IconBox
import com.example.healthcare.peresentation.common.composables.MyScaffold
import com.example.healthcare.peresentation.common.composables.NotificationListItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun NotificationScreen(
    navigator: DestinationsNavigator,
    viewModel: NotificationViewModel = koinViewModel()
) {
    val notifications = viewModel.notifications.collectAsState()

    NotificationScreenContent(
        notifications.value,
        onBackPressed = {
            navigator.popBackStack()
        },
        onDelete = {
            viewModel.deleteNotification(arrayListOf(it))
        },
        onDeleteAll = {
            viewModel.deleteNotification(notifications.value.map { it.id })
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreenContent(
    notifications: List<Notification> = emptyList(),
    onBackPressed: () -> Unit = {},
    onDelete: (String) -> Unit = {},
    onDeleteAll: () -> Unit = {}
) {
    MyScaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {  },
                actions = {
                    TextButton(onClick = { onDeleteAll()}) {
                        Text(text = "Delete All")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed()}) {
                        IconBox(imageVector = FontAwesomeIcons.Solid.ArrowLeft)
                    }
                }
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(15.dp)) {
            items(notifications){
                NotificationListItem(notification = it){
                    onDelete(it.id)
                }
            }
        }
    }
}

@Preview
@Composable
fun NotificationScreenPreview() {
    NotificationScreenContent(
        listOf(
            Notification(
                "",
                "New Appointment",
                "You have a request for new appointment from John Do",
                false,
                "10-10-2020 10:10:10"
            ),
            Notification(
                "",
                "New Appointment",
                "You have a request for new appointment from John Do",
                true,
                "10-10-2020 10:10:10"
            )
        )
    )
}

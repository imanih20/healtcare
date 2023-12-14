package com.example.healthcare.peresentation.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.healthcare.domain.clinic.Clinic
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ChevronDown
import compose.icons.fontawesomeicons.solid.ChevronUp

@Composable
fun Spinner(
    modifier : Modifier = Modifier,
    options: List<Clinic>,
    selectedIndex: Int,
    onSelectedItem: (Clinic) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    val icon = if (expanded)
        FontAwesomeIcons.Solid.ChevronUp
    else
        FontAwesomeIcons.Solid.ChevronDown

    Column{
        Box(
            modifier = Modifier.clickable {
                expanded = !expanded
            }.clip(RoundedCornerShape(30.dp)).background(MaterialTheme.colorScheme.background,
                RoundedCornerShape(30.dp)
            ).padding(10.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(5.dp)
            ) {
                Text(options.elementAtOrNull(selectedIndex)?.name?:"Select your clinic")
                Box(Modifier.size(25.dp)) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null
                    )
                }
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = modifier
        ) {
            options.forEachIndexed { index, s ->
                DropdownMenuItem(
                    text = {
                        Text(text = s.name)
                    },
                    onClick = {
                        onSelectedItem(s)
                        expanded = false
                    }
                )
            }
        }
    }
}
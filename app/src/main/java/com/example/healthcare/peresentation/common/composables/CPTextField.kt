package com.example.healthcare.peresentation.common.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loftymr.countrycp.Country

@Composable
fun CPTextField(
    value: String,
    onValueChange: (String)->Unit,
    onFullNumber: (String)->Unit,
    modifier : Modifier = Modifier,
    showError: Boolean = false,
) {
    MyTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
            onFullNumber(Country.UnitedKingdom.countryPhoneCode+it)
        },
        modifier = modifier,
        label = "Phone Number",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        leading = {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = LinearOutSlowInEasing
                        )
                    ),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = Country.UnitedKingdom.countryPhoneCode,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 4.dp),
                    fontSize = 18.sp,
                    color = Color(0xFF9B9B9B),
                )
            }
        }
    )
}
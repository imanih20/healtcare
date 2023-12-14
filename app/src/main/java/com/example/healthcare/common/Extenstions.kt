package com.example.healthcare.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun Dp.dpToPx() = with(LocalDensity.current){this@dpToPx.toPx()}


fun SnapshotStateMap<String,Boolean>.falseAll(){
    this.filter {
        it.value
    }.forEach {
        put(it.key,false)
    }
}
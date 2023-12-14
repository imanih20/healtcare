package com.example.healthcare.data.common.modules

import com.example.healthcare.common.Prefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val prefsModule = module {
    factory { Prefs(androidContext()) }
}
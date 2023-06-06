package com.example.plantbook.di

import com.example.plantbook.utils.Utils
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val utilsModule = module {
    single { Utils(androidContext()) }
}
package com.example.plantbook.di

import com.example.plantbook.ui.details.DetailPresenter
import com.example.plantbook.ui.main.MainPresenter
import org.koin.dsl.module

val presenterModule = module {
    factory { MainPresenter(get()) }
    factory { DetailPresenter(get()) }
}
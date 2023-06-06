package com.example.plantbook.ui.details

import com.example.plantbook.data.Plants
import moxy.MvpView

interface IDetailActivity: MvpView {

    fun setOfPlant(plants: Plants)
    fun close()
}
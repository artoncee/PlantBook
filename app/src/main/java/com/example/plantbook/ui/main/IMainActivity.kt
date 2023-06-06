package com.example.plantbook.ui.main

import com.example.plantbook.data.Plants
import moxy.MvpView
import moxy.viewstate.strategy.alias.OneExecution

interface IMainActivity: MvpView {

    fun showPlantList(plantsList: List<Plants>)

    @OneExecution
    fun openDetails(plants: Plants)

    @OneExecution
    fun addingPlant()
}

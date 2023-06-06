package com.example.plantbook.ui.main

import com.example.plantbook.data.Plants
import com.example.plantbook.utils.Utils
import moxy.MvpPresenter

class MainPresenter(private val utils: Utils):
    MvpPresenter<IMainActivity>() {

    private lateinit var plantsList: MutableList<Plants>
    override fun attachView(view: IMainActivity?) {
        super.attachView(view)
        plantsList = utils.getList()
        viewState.showPlantList(plantsList)
    }

    fun processDialogButtonClick(name: String, type: String, heigh: String, details: String) {
        plantsList.add(Plants(name, type, heigh, details))
        utils.saveList(plantsList)
        viewState.showPlantList(plantsList)
    }

    fun onPlantClick(position: Int) {
        viewState.openDetails(plantsList[position])
    }

    fun processDeleteClick(position: Int) {
        plantsList.removeAt(position)
        utils.saveList(plantsList)
        viewState.showPlantList(plantsList)
    }
    fun filter(filter: String) {
        val filteredPlants = plantsList.filter { plant ->
            plant.name.contains(filter, ignoreCase = true)
        }
        viewState.showPlantList(filteredPlants)
    }
}


package com.example.plantbook.ui.details

import com.example.plantbook.data.Plants
import com.example.plantbook.utils.Utils
import moxy.MvpPresenter

class DetailPresenter(private val utils: Utils): MvpPresenter<IDetailActivity>() {

    lateinit var plants: Plants

    override fun onFirstViewAttach() {
        viewState.setOfPlant(plants)
    }
    fun processDeleteClick() {
        utils.deletePlant(plants)
        viewState.close()
    }
}
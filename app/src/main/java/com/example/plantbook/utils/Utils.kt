package com.example.plantbook.utils

import android.content.Context
import com.example.plantbook.data.Plants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class Utils(private val context: Context) {
    private val path = "${context.cacheDir}/plant.json"

    fun getList(): MutableList<Plants> {
        var result = ""
        val isFileExists = File(path).exists()
        val inputStream = if (isFileExists) {
            File(path).inputStream()
        } else {
            context.assets.open("plant.json")
        }
        try {
            result = inputStream.bufferedReader().use { it.readText() }
            if (!isFileExists) writeDataToCacheFile(result)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            inputStream.close()
        }
        val typeOfList = object: TypeToken<List<Plants>>() {}.type
        return Gson().fromJson(result, typeOfList)
    }

    fun saveList(list: List<Plants>) {
        val result = Gson().toJson(list)
        writeDataToCacheFile(result)
    }

    fun deletePlant(plants: Plants) {
        val List = getList()
        List.remove(plants)
        saveList(List)
    }


    private fun writeDataToCacheFile(result: String) {
        if (!File(path).exists()) {
            File(path).createNewFile()
        }
        val outputStream = File(path).outputStream()
        try {
            outputStream.bufferedWriter().use { it.write(result) }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            outputStream.close()
        }
    }

}
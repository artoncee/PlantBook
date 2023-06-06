package com.example.plantbook.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plantbook.R
import com.example.plantbook.data.Plants
import com.example.plantbook.databinding.ActivityMainBinding
import com.example.plantbook.databinding.AddItemBinding
import com.example.plantbook.ui.details.DetailActivity
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import org.koin.android.ext.android.get

class MainActivity: MvpAppCompatActivity(), IMainActivity {


    private lateinit var binding: ActivityMainBinding
    private val presenter by moxyPresenter { get<MainPresenter>() }
    private lateinit var adapter: PlantAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        layoutManager = LinearLayoutManager(this)

        adapter = PlantAdapter(presenter::onPlantClick,  presenter::processDeleteClick)

        binding.rcView.layoutManager = layoutManager
        binding.rcView.adapter=adapter
        binding.addingButton.setOnClickListener{addingPlant()}
        setSupportActionBar(binding.toolbar)


        binding.search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                presenter.filter(newText)
                return true
            }
        })

    }
    override fun showPlantList(plants_: List<Plants>) {
        adapter.setItems(plants_)
    }

    override fun openDetails(plants: Plants) {
        startActivity(DetailActivity.makeIntent(this, plants))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.sortPlant-> adapter.sortNamePlant()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun addingPlant() {
        val dialog = android.app.AlertDialog.Builder(this)
        val dialogView= AddItemBinding.inflate(layoutInflater)
        dialog.setNegativeButton("Не надо...") { d, _ ->
            d.dismiss()
        }
        dialog.setPositiveButton("Добавить") { d, _ ->
            if(dialogView.name1.text.isNotEmpty()){
                presenter.processDialogButtonClick(
                    dialogView.name1.text.toString(),
                    dialogView.type1.text.toString(),
                    dialogView.heigh1.text.toString(),
                    dialogView.detail1.text.toString()
                )
            }
            else Toast.makeText(this, "Заполните первые 2 поля", Toast.LENGTH_SHORT).show()
            d.dismiss()
        }
        dialog.setView(dialogView.root)
        dialog.show()
    }

    private fun search(query: String) {
        adapter.filter(query)
    }
}
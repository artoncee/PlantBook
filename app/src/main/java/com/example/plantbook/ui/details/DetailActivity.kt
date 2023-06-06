package com.example.plantbook.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.plantbook.R
import com.example.plantbook.data.Plants
import com.example.plantbook.databinding.ActivityDetailBinding
import com.example.plantbook.utils.Constants
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import org.koin.android.ext.android.get

class DetailActivity : MvpAppCompatActivity(), IDetailActivity {
    companion object {
        fun makeIntent(context: Context, plants: Plants) =
            Intent(context, DetailActivity::class.java).putExtra(Constants.Extras.PLANT, plants)
    }
    private lateinit var binding: ActivityDetailBinding

    private val presenter by moxyPresenter { get<DetailPresenter>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.plants = intent.getParcelableExtra(Constants.Extras.PLANT) ?: throw IllegalArgumentException("")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back)
    }

    override fun setOfPlant(plants: Plants) {
        if(plants.name=="Жасмин"){
            binding.imageDetail.setImageResource(R.drawable.plant1)
        }
        else if(plants.name=="Василек"){
            binding.imageDetail.setImageResource(R.drawable.plant2)
        }
        else if(plants.name=="Ревень"){
            binding.imageDetail.setImageResource(R.drawable.plant3)
        }
        else if(plants.name=="Эхинацея"){
            binding.imageDetail.setImageResource(R.drawable.plant4)
        }
        else if(plants.name=="Тмин"){
            binding.imageDetail.setImageResource(R.drawable.plant5)
        }
        binding.textDetail.text= plants.details
        binding.textHeigh.text=plants.heigh
        binding.textType.text=plants.type
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.del -> {
                presenter.processDeleteClick()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    override fun close() {
        finish()
    }
}

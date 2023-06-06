package com.example.plantbook.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.plantbook.R
import com.example.plantbook.data.Plants
import com.example.plantbook.databinding.PlantItemBinding
import com.example.plantbook.utils.replaceWith

class PlantAdapter (private val listener : (Int) -> Unit,
                    private val onItemDeleteClick: (Int) -> Unit): RecyclerView.Adapter<PlantAdapter.PlantHolder>(),
    Filterable {
    private var plants = mutableListOf<Plants>()
    private var plantListFull=plants
    private var isSort = false

    class PlantHolder private constructor(private val binding: PlantItemBinding,
                                          private val listener : (Int) -> Unit,
                                          private val onItemDeleteClick: (Int) -> Unit):


        RecyclerView.ViewHolder(binding.root){
        companion object {
            fun create(parent: ViewGroup, listener: (Int) -> Unit, onItemDeleteClick: (Int) -> Unit): PlantHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PlantItemBinding.inflate(layoutInflater, parent, false)
                return PlantHolder(binding, listener, onItemDeleteClick)
            }
        }

        fun bindView (plants: Plants){
            binding.textView.text=plants.name
            binding.textView1.text=plants.type
            binding.textView2.text=plants.heigh
            binding.deleteImageView.setOnClickListener{onItemDeleteClick.invoke(adapterPosition)}
            if(plants.name=="Жасмин"){
                binding.imageView.setImageResource(R.drawable.plant1)
            }
            else if(plants.name=="Василек"){
                binding.imageView.setImageResource(R.drawable.plant2)
            }
            else if(plants.name=="Ревень"){
                binding.imageView.setImageResource(R.drawable.plant3)
            }
            else if(plants.name=="Эхинацея"){
                binding.imageView.setImageResource(R.drawable.plant4)
            }
            else if(plants.name=="Тмин"){
                binding.imageView.setImageResource(R.drawable.plant5)
            }
            else
                binding.imageView.setImageResource(R.drawable.plant6)
            // binding.imageView.setImageResource(arr[adapterPosition%5])
        }
        init {
            itemView.setOnClickListener { listener.invoke(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PlantHolder.create(parent, listener, onItemDeleteClick)

    override fun onBindViewHolder(holder: PlantHolder, position: Int) {
        holder.bindView(plants[position])
    }

    override fun getItemCount(): Int = plants.size

    fun setItems(items: List<Plants>) {
        this.plants.replaceWith(items)
        this.plantListFull=ArrayList(items)
        notifyDataSetChanged()
    }
    fun sortNamePlant() {
        if (isSort) {
            plants.sortBy { it.name.lowercase() }
        } else {
            plants.sortByDescending { it.name.lowercase() }
        }
        notifyDataSetChanged()
        isSort = !isSort
    }


    fun filter(filterString: String) {
        val filteredList: ArrayList<Plants> = ArrayList()

        for (plant in plantListFull) {
            if (plant.name.lowercase().contains(filterString.lowercase())) {
                filteredList.add(plant)
            }
        }
        plants = filteredList
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList: ArrayList<Plants> = ArrayList()

                if (constraint == null || constraint.isEmpty()) {
                    filteredList.addAll(plantListFull)
                } else {
                    val filterPattern = constraint.toString().lowercase().trim()

                    for (plant in plantListFull) {
                        if (plant.name.lowercase().contains(filterPattern)) {
                            filteredList.add(plant)
                        }
                    }
                }

                val results = FilterResults()
                results.values = filteredList

                return results
            }
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                plants.clear()
                plants.addAll(results?.values as List<Plants>)
                notifyDataSetChanged()
            }
        }
    }
}


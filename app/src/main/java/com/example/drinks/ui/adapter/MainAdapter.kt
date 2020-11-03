package com.example.drinks.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.drinks.databinding.DrinkListItemBinding
import com.example.drinks.domain.model.Drink

class MainAdapter(private val context: Context, private val drinkList: List<Drink>, private val itemClickedListener: OnDrinkListener): RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnDrinkListener {
        fun onDrinkClicked(item: Drink)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = DrinkListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = MainViewHolder(itemBinding)
        itemBinding.root.setOnClickListener {
            itemClickedListener.onDrinkClicked(drinkList[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(drinkList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return drinkList.size
    }

    inner class MainViewHolder(private val binding: DrinkListItemBinding): BaseViewHolder<Drink>(binding.root) {

        override fun bind(item: Drink, position: Int) = with(binding) {
            Glide.with(context).load(item.image).centerCrop().into(drinkImage)
            drinkNameTv.text = item.name
            drinkDescriptionTv.text = item.description

        }

    }
}
package com.example.drinks.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.drinks.R
import com.example.drinks.domain.model.Drink
import kotlinx.android.synthetic.main.drink_list_item.view.*

class MainAdapter(private val context: Context, private val drinkList: List<Drink>, private val itemClickedListener: OnDrinkListener): RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnDrinkListener {
        fun onDrinkClicked(item: Drink)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.drink_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(drinkList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return drinkList.size
    }

    inner class MainViewHolder(itemView: View): BaseViewHolder<Drink>(itemView) {

        override fun bind(item: Drink, position: Int) {
            Glide.with(context).load(item.image).centerCrop().into(itemView.drink_image)
            itemView.drink_name_tv.text = item.name
            itemView.drink_description_tv.text = item.description
            itemView.setOnClickListener {
                itemClickedListener.onDrinkClicked(item)
            }
        }

    }
}
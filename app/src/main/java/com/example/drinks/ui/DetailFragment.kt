package com.example.drinks.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.drinks.R
import com.example.drinks.domain.model.Drink
import com.example.drinks.domain.model.DrinkEntity
import com.example.drinks.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var drink: Drink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            drink = it.getParcelable("drink")?: Drink()
            Log.d("Details: ", "$drink")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(drink.image).centerCrop().into(detail_drink_image)
        detail_title.text = drink.name
        detail_description.text = drink.description
        description_has_alcohol.text = drink.hasAlcohol

        description_save_drink_btn.setOnClickListener {
            viewModel.saveDrink(DrinkEntity(drink.drinkId, drink.image, drink.name, drink.description, drink.hasAlcohol))
            Toast.makeText(requireContext(), "Drink saved in favourites", Toast.LENGTH_SHORT).show()
        }
    }




}
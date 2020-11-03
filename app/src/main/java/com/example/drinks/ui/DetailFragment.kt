package com.example.drinks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.drinks.databinding.FragmentDetailBinding
import com.example.drinks.domain.model.Drink
import com.example.drinks.domain.model.DrinkEntity
import com.example.drinks.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var drink: Drink
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            drink = it.getParcelable("drink")?: Drink()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(drink.image).centerCrop().into(binding.detailDrinkImage)
        binding.detailTitle.text = drink.name
        binding.detailDescription.text = drink.description
        binding.descriptionHasAlcohol.text = drink.hasAlcohol

        binding.descriptionSaveDrinkBtn.setOnClickListener {
            viewModel.saveDrink(DrinkEntity(drink.drinkId, drink.image, drink.name, drink.description, drink.hasAlcohol))
            Toast.makeText(requireContext(), "Drink saved in favourites", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
package com.example.drinks.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drinks.R
import com.example.drinks.domain.model.Drink
import com.example.drinks.ui.adapter.MainAdapter
import com.example.drinks.ui.viewmodel.MainViewModel
import com.example.drinks.domain.model.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

@AndroidEntryPoint
class MainFragment : Fragment(), MainAdapter.OnDrinkListener {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpSearchView()
        setUpObservers()
        favourites_button.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favouriteFragment)
        }

    }

    private fun setUpObservers() {
        viewModel.fetchDrinkList.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading ->  progress_bar.visibility = View.VISIBLE
                is Resource.Success -> {
                    progress_bar.visibility = View.GONE
                    Log.d("mainFrag", "${it.data}")
                    rv_drinks_list.adapter = MainAdapter(requireContext(), it.data, this)
                }
                is Resource.Failure -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(requireContext(), "An error occurred fetching data: ${it.exception.message}", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setUpSearchView() {

        search_view.setOnQueryTextListener(object: SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setDrink(query!!)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
    }

    private fun setUpRecyclerView() {
        rv_drinks_list.layoutManager = LinearLayoutManager(requireContext())
        rv_drinks_list.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    override fun onDrinkClicked(item: Drink) {
        val bundle = Bundle()
        bundle.putParcelable("drink", item)
        findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
    }
}
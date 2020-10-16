package com.example.drinks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drinks.R
import com.example.drinks.data.DataSource
import com.example.drinks.data.model.Drink
import com.example.drinks.domain.RepoImpl
import com.example.drinks.ui.viewmodel.MainAdapter
import com.example.drinks.ui.viewmodel.MainViewModel
import com.example.drinks.ui.viewmodel.VMFactory
import com.example.drinks.vo.Resource
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), MainAdapter.OnDrinkListener {

    private val viewModel by viewModels<MainViewModel> { VMFactory(RepoImpl(DataSource())) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        viewModel.fetchDrinkList.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading ->  progress_bar.visibility = View.VISIBLE
                is Resource.Success -> {
                    progress_bar.visibility = View.GONE
                    rv_drinks_list.adapter = MainAdapter(requireContext(), it.data, this)
                }
                is Resource.Failure -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(requireContext(), "An error occurred fetching data ${it.exception}", Toast.LENGTH_SHORT).show()
                }
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
        findNavController().navigate(R.id.detailFragment, bundle)
    }
}
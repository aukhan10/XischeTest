package com.asad.xischetest.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asad.xischetest.R
import com.asad.xischetest.base.BaseFragment
import com.asad.xischetest.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewmodel by viewModels()
    private lateinit var listAdapter: CountryListAdapter
    private lateinit var countryList: List<Country>
    private lateinit var cityList: List<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCountries()
    }

    override fun initializeView() {
        listAdapter = CountryListAdapter(mutableListOf()) {
            val bundle = bundleOf(
                "name" to it
            )
            findNavController().navigate(
                R.id.action_homeFragment_to_detailFragment, bundle
            )
        }

        binding.listRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = listAdapter
        }

        binding.svCountry.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                setCountryList(newText.orEmpty())
                return false
            }

        })
    }

    override fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect {
                countryList = it.countryList
                cityList = it.cityList
            }
        }
    }

    private fun setCountryList(query: String) {
        if (query.isNotEmpty()) {
            val cities = cityList.filter { it.contains(query, ignoreCase = true) }
            listAdapter.update(cities)
        } else {
            listAdapter.update(mutableListOf())
        }

    }
}
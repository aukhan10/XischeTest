package com.asad.xischetest.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asad.xischetest.base.BaseFragment
import com.asad.xischetest.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewmodel by viewModels()
    private var cityName: String = ""
    private lateinit var listAdapter: WeatherListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cityName = arguments?.getString("name").orEmpty()
        if (cityName.isNotEmpty()) {
            viewModel.getWeather(cityName)
        }
    }

    override fun initializeView() {
        super.initializeView()
        listAdapter = WeatherListAdapter(mutableListOf())

        binding.listRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = listAdapter
        }
    }

    override fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect {
                setTemperature(it)
            }
        }
    }

    private fun setTemperature(weather: WeatherUiState) {
        with(binding) {
            cityName.text = weather.cityName
            currentTemperature.text = "${weather.currentTemperature}\u2103"
            currentCondition.text = weather.condition
            listAdapter.update(weather.forecastDayList)
        }

    }


}
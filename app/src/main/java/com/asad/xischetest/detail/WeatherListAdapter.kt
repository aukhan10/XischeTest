package com.asad.xischetest.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.asad.xischetest.Utils
import com.asad.xischetest.databinding.ItemWeatherBinding

class WeatherListAdapter(
    private var list: List<ForecastDay>
) : Adapter<ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemBinding = ItemWeatherBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ListViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    fun update(list: List<ForecastDay>) {
        this.list = list
        notifyDataSetChanged()
    }

}

class ListViewHolder(
    private val binding: ItemWeatherBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bindView(
        item: ForecastDay,
    ) {
        with(binding) {
            day.text = item.date?.let { Utils.getWeekdayFromDate(it) }
            temperature.text = getRange(item.day?.maxtempC?.toInt(),item.day?.mintempC?.toInt())
        }
    }

    private fun getRange(max: Int?, min: Int?): String {
        val max = "$max\u2109"
        val min = "$min\u2109"
        return "H: $max L:$min"
    }
}

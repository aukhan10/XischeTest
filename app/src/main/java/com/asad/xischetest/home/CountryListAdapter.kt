package com.asad.xischetest.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.asad.xischetest.databinding.ItemCountryBinding

class CountryListAdapter(
    private var list: List<String>,
    private var onClicked: (String) -> Unit
) : Adapter<ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemBinding = ItemCountryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ListViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bindView(list[position], onClicked)
    }

    fun update(list: List<String>) {
        this.list = list
        notifyDataSetChanged()
    }

}

class ListViewHolder(
    private val binding: ItemCountryBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bindView(
        item: String,
        onClicked: (String) -> Unit,
    ) {
        with(binding) {
            countryName.text = item
//            uniCountry.text = item.country
//            uniState.text = item.stateProvince

            itemView.setOnClickListener { onClicked(item) }
        }
    }
}

package com.health.covid19.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.health.covid19.R
import com.health.covid19.enitites.Case
import com.health.covid19.ui.main.MainFragmentDirections
import kotlinx.android.synthetic.main.country_rates_item.view.*

class CountryRatesAdapter(callback: DiffUtil.ItemCallback<Case> = diffCallback) :
    ListAdapter<Case, CountryRatesAdapter.CountryRatesViewHolder>(callback) {

    companion object {
        private val diffCallback: DiffUtil.ItemCallback<Case> =
            object : DiffUtil.ItemCallback<Case>() {
                override fun areItemsTheSame(
                    oldItem: Case,
                    newItem: Case
                ): Boolean {
                    return oldItem.country == newItem.country &&
                            oldItem.countryInfo.id == newItem.countryInfo.id
                }

                override fun areContentsTheSame(
                    oldItem: Case,
                    newItem: Case
                ): Boolean {
                    return oldItem.country == newItem.country &&
                            oldItem.countryInfo.id == newItem.countryInfo.id &&
                            oldItem.cases == newItem.cases
                }
            }
    }

    inner class CountryRatesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val confirmed_number_textView: TextView
        val recovered_number_textView: TextView
        val deaths_number_textView: TextView
        val country_name_textView: TextView
        init {
            confirmed_number_textView = itemView.confirmed_number_textView
            recovered_number_textView = itemView.recovered_number_textView
            deaths_number_textView = itemView.deaths_number_textView
            country_name_textView = itemView.country_name_textView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryRatesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.country_rates_item, parent, false)
        return CountryRatesViewHolder(view)
    }

    override fun onBindViewHolder(ratesHolder: CountryRatesViewHolder, position: Int) {
        val case = getItem(position)
        ratesHolder.country_name_textView.text = case.country
        ratesHolder.confirmed_number_textView.text = case.cases.toString()
        ratesHolder.recovered_number_textView.text = case.recovered.toString()
        ratesHolder.deaths_number_textView.text = case.deaths.toString()

        ratesHolder.itemView.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToCountryInfoPage(case.country)
            Navigation.findNavController(it).navigate(action)

        }

    }
}
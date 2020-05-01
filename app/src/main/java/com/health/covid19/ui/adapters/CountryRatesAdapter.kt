package com.health.covid19.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.health.covid19.R
import com.health.covid19.enitites.Case
import javax.inject.Inject

class CountryRatesAdapter(callback: DiffUtil.ItemCallback<Case> = diffCallback) :
    ListAdapter<Case, RecyclerView.ViewHolder>(callback) {

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

        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.country_rates_item, parent, false)
        return CountryRatesViewHolder(view)
    }

    override fun onBindViewHolder(ratesHolder: RecyclerView.ViewHolder, position: Int) {

    }
}
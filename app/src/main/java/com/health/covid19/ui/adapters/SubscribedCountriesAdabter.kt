package com.health.covid19.ui.adapters

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.health.covid19.R
import com.health.covid19.app.Covid19TrackerApp
import com.health.covid19.enitites.Case
import com.health.covid19.ui.main.MainFragmentDirections
import com.health.covid19.ui.main.SubscribedFragmentDirections
import com.health.covid19.viewmodels.CasesViewModel
import kotlinx.android.synthetic.main.subscribed_countries_item.view.*

class SubscribedCountriesAdapter(callback: DiffUtil.ItemCallback<Case> = diffCallback) :
    androidx.recyclerview.widget.ListAdapter<Case, SubscribedCountriesAdapter.SubscribedViewHolder>(callback) {
    private lateinit var model: CasesViewModel
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
    fun setViewModel( model: CasesViewModel){
        this.model=model
    }

    inner class SubscribedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val confirmed_number_textView: TextView
        val recovered_number_textView: TextView
        val deaths_number_textView: TextView
        val country_name_textView: TextView
        val unsubscribe: Button


        init {
            confirmed_number_textView = itemView.confirmed_textView
            recovered_number_textView = itemView.recovered_textView
            deaths_number_textView = itemView.deaths_textView
            country_name_textView = itemView.country_textView
            unsubscribe =itemView.unsubscribe
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscribedViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.subscribed_countries_item, parent, false)
        return SubscribedViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubscribedViewHolder, position: Int) {
        val case = getItem(position)
        holder.country_name_textView.text = case.country
        holder.confirmed_number_textView.text = case.cases.toString()
        holder.recovered_number_textView.text = case.recovered.toString()
        holder.deaths_number_textView.text = case.deaths.toString()

        holder.itemView.setOnClickListener {
            val action = SubscribedFragmentDirections.actionSubscribedFragmentToCountryInfoPage(case.country)
            Navigation.findNavController(it).navigate(action)

        }
        holder.unsubscribe.setOnClickListener {
           unsubscribe(case,position)

        }

    }

    private fun unsubscribe(case:Case,position: Int){
        case.isSubscribed=false
        model.updateCase(case)

        notifyItemRemoved(position)
    }
}
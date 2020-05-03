package com.health.covid19.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentsAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val ratesFragment = RatesFragment()
    private val statisticsFragment = StatisticsFragment()

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 ->  ratesFragment
            1 ->  statisticsFragment
            else -> StatisticsFragment()
        }
   }
}
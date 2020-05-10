package com.health.covid19.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.lifecycle.ViewModelProvider
import com.health.covid19.R
import com.health.covid19.app.Covid19TrackerApp
import com.health.covid19.util.sharedPreferencesKey
import com.health.covid19.viewmodels.CasesViewModel
import kotlinx.android.synthetic.main.settings_fragment.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsFragment : Fragment() {
    private lateinit var app: Covid19TrackerApp
    @Inject
    lateinit var preferences: SharedPreferences
    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory
    private lateinit var model: CasesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setupSettings(view)
    }

    private fun init() {
        app = activity?.application as Covid19TrackerApp
        app.covid19TrackerComponent.inject(this)
        model = modelFactory.create(CasesViewModel::class.java)
    }

    private fun setupSettings(view: View) {

        when(preferences.getLong(sharedPreferencesKey, 2)){
            2L -> view.two_hours_radioButton.isChecked = true
            5L -> view.five_hours_radioButton.isChecked = true
            8L -> view.eight_hours_radioButton.isChecked = true
        }

        view.UnsubscribeAll.setOnClickListener{
            model.unsbcribeAllCountries()
        }

        view.two_hours_radioButton.setOnClickListener {
            if ((it as RadioButton).isChecked) {
                view.five_hours_radioButton.isChecked = false
                view.eight_hours_radioButton.isChecked = false
               setWorkInterval(2)

            }

        }

        view.five_hours_radioButton.setOnClickListener {
            if ( (it as RadioButton).isChecked) {
                view.two_hours_radioButton.isChecked = false
                view.eight_hours_radioButton.isChecked = false
                setWorkInterval(5)
            }

        }

        view.eight_hours_radioButton.setOnClickListener {
            if ((it as RadioButton).isChecked) {
                view.five_hours_radioButton.isChecked = false
                view.two_hours_radioButton.isChecked = false
                setWorkInterval(8)
            }

        }
    }

    private fun setWorkInterval(interval: Long) {
        app.setupWorkManager(interval, false)
        CoroutineScope(Dispatchers.IO).launch {
            preferences.edit().putLong(sharedPreferencesKey, interval).apply()
        }
    }

}

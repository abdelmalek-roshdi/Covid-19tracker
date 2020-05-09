package com.health.covid19.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.health.covid19.R
import kotlinx.android.synthetic.main.fragment_statistics.*
import lecho.lib.hellocharts.model.PieChartData
import lecho.lib.hellocharts.model.SliceValue
import java.lang.Integer.parseInt


class StatisticsFragment: Fragment() {

    private lateinit var data: PieChartData


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

       val view = inflater.inflate(R.layout.fragment_statistics, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setChart()
        america.setOnClickListener {

        }
     }

    fun setChart(){
        var values: ArrayList<SliceValue> = ArrayList()

        val sliceAfrica = SliceValue(Math.random().toFloat() * 30 + 15,
            Color.parseColor("#53676C"))
        val sliceAsia = SliceValue(Math.random().toFloat() * 30 + 15,  Color.parseColor("#FF9955"))
        val sliceAustralia = SliceValue(Math.random().toFloat() * 30 + 15, Color.parseColor("#00AAD4"))
        val sliceEurope = SliceValue(Math.random().toFloat() * 30 + 15, Color.parseColor("#C83771"))
        val sliceNorthAmerica = SliceValue(Math.random().toFloat() * 30 + 15, Color.parseColor("#FFCC00"))
        val slicesouthAmerica = SliceValue(Math.random().toFloat() * 30 + 15, Color.parseColor("#00AA00"))

        values.add(sliceAfrica)
        values.add(sliceAsia)
        values.add(sliceAustralia)
        values.add(sliceEurope)
        values.add(sliceNorthAmerica)
        values.add(slicesouthAmerica)

        data =PieChartData(values);

        data.setHasLabels(false);

        data.setHasLabelsOnlyForSelected(false);

        data.setHasLabelsOutside(false);

        data.setHasCenterCircle(true);
        chart.setPieChartData(data);
    }


}
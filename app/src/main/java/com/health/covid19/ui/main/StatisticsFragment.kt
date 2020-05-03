package com.health.covid19.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.health.covid19.R
import kotlinx.android.synthetic.main.fragment_statistics.*


class StatisticsFragment: Fragment() {
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
        america.setOnClickListener {
            print("_________________________________________")
            Log.i("OMN","this is spartaaaaa3 $statsticsCard.width")
            //statsticsCard.layoutParams=
           // val layoutParams =
             //   statsticsCard.getLayoutParams()
           // layoutParams.height = height
            //layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
           // africaStat.setLayoutParams(layoutParams)


        }



    }
//    fun printOnClick(){
//      error("Clicked______________________________________")
//       Log.i("OMN","this is spartaaaaa3")
//    }
}
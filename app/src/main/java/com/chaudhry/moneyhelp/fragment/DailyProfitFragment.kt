package com.chaudhry.moneyhelp.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.chaudhry.moneyhelp.R
import com.chaudhry.moneyhelp.adapter.DailyAdapter
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS
import kotlin.collections.ArrayList

class DailyProfitFragment : Fragment() {


    lateinit var root :View
    lateinit var rvDailyProfit : RecyclerView
    lateinit var imgDate : ImageView
    lateinit var txtDate :TextView
    var list = ArrayList<String>()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_daily_profit, container, false)
        rvDailyProfit = root.findViewById(R.id.rvDailyProfit)
        imgDate = root.findViewById(R.id.imgDate)
        txtDate = root.findViewById(R.id.txtDate)

        list.add("Silver")
        list.add("Gold")
        list.add("Zinc")
        list.add("Lead")
        list.add("Aluminium")
        list.add("Natural Gas")
        list.add("Copper")
        list.add("Nickel")

        rvDailyProfit.adapter = DailyAdapter(list)

        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = sdf.format(Date())
        txtDate.setText(currentDate)

        onClick()
    return root;
    }

    private fun onClick() {
        imgDate.setOnClickListener {
            val c = Calendar.getInstance()

            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(activity!!, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                // Display Selected date in textbox
                var monthYear: String
                var dayMonth: String
                if((monthOfYear+1)<10){
                    monthYear =  "0"+ (monthOfYear+1)
                }else{
                    monthYear = (monthOfYear+1).toString()
                }
                if((dayOfMonth)<10){
                    dayMonth =  "0"+ (dayOfMonth)
                }else{
                    dayMonth = (dayOfMonth).toString()
                }

                txtDate.setText("" + dayMonth + "/" + monthYear + "/" + year)

            }, year, month, day)
            dpd.show()
        }
    }

}
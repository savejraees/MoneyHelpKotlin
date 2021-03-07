package com.chaudhry.moneyhelp.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.chaudhry.moneyhelp.R
import com.chaudhry.moneyhelp.adapter.DailyAdapter
import com.chaudhry.moneyhelp.adapter.MonthlyAdapter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MonthlyProfitFragment : Fragment() {

    lateinit var root : View
    lateinit var rvDailyProfit : RecyclerView
    var list = ArrayList<String>()

    lateinit var imgDateMonth : ImageView
    lateinit var txtDateMonth : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_monthly, container, false)
        rvDailyProfit = root.findViewById(R.id.rvDailyProfit)
        imgDateMonth = root.findViewById(R.id.imgDateMonth)
        txtDateMonth = root.findViewById(R.id.txtDateMonth)

        list.add("Silver")
        list.add("Gold")
        list.add("Zinc")
        list.add("Lead")
        list.add("Aluminium")
        list.add("Natural Gas")
        list.add("Copper")
        list.add("Nickel")

        rvDailyProfit.adapter = MonthlyAdapter(list)


        val year = Calendar.getInstance().get(Calendar.YEAR);
        val cal = Calendar.getInstance()
        val month_date = SimpleDateFormat("MMM")
        val month_name = month_date.format(cal.time)
        txtDateMonth.setText(month_name + "/" + year)

        onClick()

        return root
    }

    private fun onClick() {
        imgDateMonth.setOnClickListener {
            val c = Calendar.getInstance()

            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(
                activity!!,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    // Display Selected date in textbox
                    lateinit var monthYear: String

                    if ((monthOfYear) == 0) {
                        monthYear = "Jan"
                    } else if ((monthOfYear) == 1) {
                        monthYear = "Feb"
                    } else if ((monthOfYear) == 2) {
                        monthYear = "March"
                    } else if ((monthOfYear) == 3) {
                        monthYear = "Apr"
                    } else if ((monthOfYear) == 4) {
                        monthYear = "May"
                    } else if ((monthOfYear) == 5) {
                        monthYear = "Jun"
                    } else if ((monthOfYear) == 6) {
                        monthYear = "Jul"
                    } else if ((monthOfYear) == 7) {
                        monthYear = "Aug"
                    } else if ((monthOfYear) == 8) {
                        monthYear = "Sept"
                    } else if ((monthOfYear) == 9) {
                        monthYear = "Oct"
                    } else if ((monthOfYear) == 10) {
                        monthYear = "Nov"
                    } else if ((monthOfYear) == 11) {
                        monthYear = "Dec"
                    }


                    txtDateMonth.setText("$monthYear/$year")

                },
                year,
                month,
                day
            )
            dpd.show()
        }
    }


    private fun createDialogWithoutDateField(): DatePickerDialog? {
        val dpd = DatePickerDialog(activity!!, null, 2014, 1, 24)
        try {
            val datePickerDialogFields = dpd.javaClass.declaredFields
            for (datePickerDialogField in datePickerDialogFields) {
                if (datePickerDialogField.name == "mDatePicker") {
                    datePickerDialogField.isAccessible = true
                    val datePicker = datePickerDialogField[dpd] as DatePicker
                    val datePickerFields = datePickerDialogField.type.declaredFields
                    for (datePickerField in datePickerFields) {
                        Log.i("test", datePickerField.name)
                        if ("mDaySpinner" == datePickerField.name) {
                            datePickerField.isAccessible = true
                            val dayPicker = datePickerField[datePicker]
                            (dayPicker as View).visibility = View.GONE
                        }
                    }
                }
            }
        } catch (ex: Exception) {
        }
        return dpd
    }
}
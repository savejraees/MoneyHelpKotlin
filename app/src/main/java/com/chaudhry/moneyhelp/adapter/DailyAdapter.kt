package com.chaudhry.moneyhelp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chaudhry.moneyhelp.R
import kotlinx.android.synthetic.main.row_daily_profit.view.*

class DailyAdapter(val list: ArrayList<String>) : RecyclerView.Adapter<DailyAdapter.DailyHolder>() {

    class  DailyHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_daily_profit,parent,false)
        return DailyHolder(v)
    }

    override fun onBindViewHolder(holder: DailyHolder, position: Int) {
        holder.view.txtNameDailyProduct.text = list[position]

        if(position%2==0){
            holder.view.txtPriceDailyProd.setTextColor(Color.parseColor("#0CC513"))
            holder.view.txtPriceDailyProd.setText("20135486")
        }else{
            holder.view.txtPriceDailyProd.setTextColor(Color.parseColor("#F82C6D"))
            holder.view.txtPriceDailyProd.setText("-135486")
        }
    }

    override fun getItemCount(): Int {
        return if (list.isNullOrEmpty()) 0 else list.size
    }
}
package com.chaudhry.moneyhelp.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chaudhry.moneyhelp.R
import com.chaudhry.moneyhelp.model.HomeData.Service
import kotlinx.android.synthetic.main.row_home.view.*

class HomeAdapter(val list: ArrayList<Service>) : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {


    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.view.txtCategory.text = list[position].category.name
        holder.view.txtBUYSEll.text = list[position].type
        holder.view.txtSignal.text = list[position].buy
        holder.view.txtCmp.text = list[position].compound
        holder.view.txtResult.text = list[position].result
        holder.view.txtTarget.text = list[position].target
        holder.view.txtStopLoss.text = list[position].stop_loss
        holder.view.txtInstruct.text = list[position].interest
        holder.view.txtDats.text = list[position].date
        holder.view.txtNoticed.text = list[position].description


        if(list[position].type.equals("Buy")){
            holder.view.txtSignal.setBackgroundColor(Color.parseColor("#0CC513"))
            holder.view.txtCmp.setBackgroundColor(Color.parseColor("#0CC513"))
            holder.view.txtResult.setBackgroundColor(Color.parseColor("#0CC513"))
            holder.view.line.setBackgroundColor(Color.parseColor("#0CC513"))
            holder.view.txtTarget.setBackgroundColor(Color.parseColor("#8A021D"))
            holder.view.txtStopLoss.setBackgroundColor(Color.parseColor("#8A021D"))

        }
        else{
            holder.view.txtSignal.setBackgroundColor(Color.parseColor("#8A021D"))
            holder.view.txtCmp.setBackgroundColor(Color.parseColor("#8A021D"))
            holder.view.txtResult.setBackgroundColor(Color.parseColor("#8A021D"))
            holder.view.line.setBackgroundColor(Color.parseColor("#8A021D"))
            holder.view.txtTarget.setBackgroundColor(Color.parseColor("#0CC513"))
            holder.view.txtStopLoss.setBackgroundColor(Color.parseColor("#0CC513"))

        }
    }

    override fun getItemCount(): Int {
        return if (list.isNullOrEmpty()) 0 else list.size
    }

    class HomeHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {

        val regNumberListView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_home, parent, false)
        return HomeHolder(regNumberListView)
    }
}
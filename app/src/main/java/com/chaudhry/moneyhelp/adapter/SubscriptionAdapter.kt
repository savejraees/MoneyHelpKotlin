package com.chaudhry.moneyhelp.adapter

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chaudhry.moneyhelp.R
import com.chaudhry.moneyhelp.model.Subscription.Subscription
import kotlinx.android.synthetic.main.row_subcribe.view.*

class SubscriptionAdapter(val list: List<Subscription>,var context : Context) :RecyclerView.Adapter<SubscriptionAdapter.SubscriptionHolder>() {


    class SubscriptionHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_subcribe, parent, false)
        return SubscriptionHolder(v)
    }

    override fun onBindViewHolder(holder: SubscriptionHolder, position: Int) {
       holder.view.txtDays.text = list[position].days.toString()
        holder.view.btnRupees.text = list[position].price.toString()

        holder.view.btnRupees.setOnClickListener {
            showDialog(position)
        }
    }

    private fun showDialog(position: Int) {
        val dialog = Dialog(context)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_payment)
        val txtYes = dialog.findViewById(R.id.txtYes) as TextView
        val txtNo = dialog.findViewById(R.id.txtNo) as TextView
        txtNo.setOnClickListener {
            dialog.dismiss()
        }
        txtYes.setOnClickListener { dialog.dismiss() }
        dialog.show()

    }

    override fun getItemCount(): Int {
        return if(list.isNullOrEmpty()) 0 else list.size
    }

}
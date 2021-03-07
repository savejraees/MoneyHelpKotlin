package com.chaudhry.moneyhelp.activity

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.chaudhry.moneyhelp.R
import com.chaudhry.moneyhelp.adapter.SubscriptionAdapter
import com.chaudhry.moneyhelp.model.Subscription.Data
import com.chaudhry.moneyhelp.model.Subscription.Subscription
import com.chaudhry.moneyhelp.util.RetrofitFactory.api
import com.chaudhry.moneyhelp.util.SessonManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class SubscriptionActivity : AppCompatActivity() {

    private lateinit var list : ArrayList<Subscription>
    lateinit var rvSubscribe: RecyclerView
    private lateinit var sessonManager: SessonManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subscription)
        rvSubscribe = findViewById(R.id.rvSubscribe)
        sessonManager = SessonManager.getInstance(this@SubscriptionActivity)!!

        GlobalScope.launch(Dispatchers.Main){
            val response = api.getSubscription("Bearer "+sessonManager.token)
            try {
                if(response.isSuccessful){
                  if(response.body()?.status.equals("success")){
                      val data : Data? = response.body()?.data
                       list  = data?.subscriptions as ArrayList<Subscription>
                      rvSubscribe.adapter = SubscriptionAdapter(list,this@SubscriptionActivity)

                  }else{
                      Toast.makeText(this@SubscriptionActivity, "${response.body()?.message}", Toast.LENGTH_SHORT).show()
                  }
                }
            }catch (e : Exception){
                Toast.makeText(this@SubscriptionActivity,"${e.message}",Toast.LENGTH_SHORT).show()
            }

        }
    }
}
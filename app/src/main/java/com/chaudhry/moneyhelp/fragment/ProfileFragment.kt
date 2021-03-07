package com.chaudhry.moneyhelp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.chaudhry.moneyhelp.R
import com.chaudhry.moneyhelp.activity.LoginActivity
import com.chaudhry.moneyhelp.activity.SubscriptionActivity
import com.chaudhry.moneyhelp.util.RetrofitFactory.api
import com.chaudhry.moneyhelp.util.SessonManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import java.lang.Exception

class ProfileFragment : Fragment() {

    lateinit var root: View
    lateinit var cardSubscribe : CardView
    lateinit var cardLogout : CardView
    lateinit var txtName : TextView
    lateinit var txtMobile : TextView
    lateinit var txtRegisterDate : TextView
    lateinit var txtValidateDate : TextView
    lateinit var txtUserId : TextView
    lateinit var txtSubscription : TextView
    lateinit var sessonManager: SessonManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        root = inflater.inflate(R.layout.fragment_profile, container, false)
        cardSubscribe = root.findViewById(R.id.cardSubscribe)
        cardLogout = root.findViewById(R.id.cardLogout)
        txtName = root.findViewById(R.id.txtName)
        txtMobile = root.findViewById(R.id.txtMobile)
        txtRegisterDate = root.findViewById(R.id.txtRegisterDate)
        txtValidateDate = root.findViewById(R.id.txtValidateDate)
        txtUserId = root.findViewById(R.id.txtUserId)
        txtSubscription = root.findViewById(R.id.txtSubscription)
        sessonManager = SessonManager.getInstance(activity)!!

        GlobalScope.launch(Dispatchers.Main) {
            val response = api.getProfileData("Bearer "+sessonManager.token)
            try {
                if (response.isSuccessful){
                    if(response.body()?.status.toString().equals("success")){
                        txtName.setText(response.body()?.profile?.name)
                        txtMobile.setText(response.body()?.profile?.mobile)
                        txtRegisterDate.setText(response.body()?.profile?.registration_date)
                        txtValidateDate.setText(response.body()?.profile?.validity_date)
                        txtUserId.setText(response.body()?.profile?.unique_id.toString())
                        txtSubscription.setText(response.body()?.profile?.days_remaining.toString())

                    }else{
                        Toast.makeText(activity, ""+response.body()?.message, Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(activity, ""+response.message(), Toast.LENGTH_SHORT).show()
                }
            }catch (e : Exception){
                Toast.makeText(activity, ""+e.message, Toast.LENGTH_SHORT).show()
            }
        }
        cardSubscribe.setOnClickListener {
            startActivity(Intent(activity, SubscriptionActivity::class.java))
        }

        cardLogout.setOnClickListener {
            sessonManager.removePreference()
            Toast.makeText(activity, "Logout Successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finishAffinity()
        }
    return root
    }

}
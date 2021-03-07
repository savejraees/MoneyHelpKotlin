package com.chaudhry.moneyhelp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.chaudhry.moneyhelp.R
import com.chaudhry.moneyhelp.util.MyApi
import com.chaudhry.moneyhelp.util.RetrofitFactory.api
import com.chaudhry.moneyhelp.util.SessonManager
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    val BaseUrl = "http://moneyhelp.mobbindtechnology.com/api/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        btnLinkToLoginScreen.setOnClickListener {
            if(et_user_name_login.text.toString().isEmpty()){
                et_user_name_login.setError("Can't be blank")
                et_user_name_login.requestFocus()
            }else if(et_password_login.text.toString().isEmpty()){
                et_password_login.setError("Can't be blank")
                et_password_login.requestFocus()
            }else {
                 GlobalScope.launch(Dispatchers.Main){
                     val  response = api.postLogin(et_user_name_login.text.toString(),et_password_login.text.toString())
                     try {
                         if(response.isSuccessful){
                             if(response.body()?.status.equals("success")){
                                 Toast.makeText(
                                     this@LoginActivity,
                                     ""+response.body()?.message,
                                     Toast.LENGTH_LONG).show()

                                    val tokenGet : String? = response.body()?.token
                                    val sessonManager = SessonManager.getInstance(this@LoginActivity)

                                 if (sessonManager != null) {
                                     sessonManager.token = tokenGet
                                 }

                                 startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                                 finishAffinity()
                             }else{
                                 Toast.makeText(this@LoginActivity, "${response.body()?.message}", Toast.LENGTH_SHORT).show()
                             }
                           //      Log.d("adsdadada",response)

                         }
                     }catch (e : Exception){
                         // Show API error.
                         Toast.makeText(
                             this@LoginActivity,
                             "Error Occurred: ${e.message}",
                             Toast.LENGTH_LONG).show()
                     }
                 }
            }
        }
    }
}
package com.chaudhry.moneyhelp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.chaudhry.moneyhelp.R
import com.chaudhry.moneyhelp.util.SessonManager

class SplashActivity : AppCompatActivity() {
    private lateinit var sessonManager: SessonManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        sessonManager = SessonManager.getInstance(this@SplashActivity)!!

        Handler().postDelayed(
            {
                if(sessonManager.token?.isEmpty() == true){
                    startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
                    finishAffinity()
                }else{
                    startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                    finishAffinity()
                }

            },4000
        )
    }
}
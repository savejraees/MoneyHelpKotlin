package com.chaudhry.moneyhelp.util

import android.content.Context
import android.content.SharedPreferences

class SessonManager private constructor(context: Context){
    private  val sharedPreferences :SharedPreferences = context.getSharedPreferences("SharedPref",Context.MODE_PRIVATE)
    private var editor : SharedPreferences.Editor

    init {
        editor = sharedPreferences.edit()
    }

    companion object{
        private var sessonManager : SessonManager? = null
        fun getInstance(context: Context?): SessonManager?{
            if(sessonManager==null){
                sessonManager = context?.let { SessonManager(it) }
            }
            return sessonManager
        }
    }

    fun removePreference() {
        editor.clear().apply()
    }

    var token : String?
          get() = sharedPreferences.getString("token","")
          set(value){
              editor.putString("token",value).apply()
          }

}
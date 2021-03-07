package com.chaudhry.moneyhelp.util

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {
    val BaseUrl = "http://moneyhelp.mobbindtechnology.com/api/"

    private val retrofit by lazy {
        val client= OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .readTimeout(60, TimeUnit.MINUTES)
            .connectTimeout(60,TimeUnit.MINUTES)
            .build()

        Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()


    }

    val api by lazy {
        retrofit.create(MyApi::class.java)
    }
}
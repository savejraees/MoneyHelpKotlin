package com.chaudhry.moneyhelp.util

import com.chaudhry.moneyhelp.model.HomeCategory.HomeCategoryMOdel
import com.chaudhry.moneyhelp.model.HomeData.HomeStatus
import com.chaudhry.moneyhelp.model.LoginModel
import com.chaudhry.moneyhelp.model.ProfileModel.ProfileStatusMOdel
import com.chaudhry.moneyhelp.model.Subscription.CustomerSubscription
import com.chaudhry.moneyhelp.model.Subscription.SubscriptionMOdel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface MyApi {

    @FormUrlEncoded
    @POST("login")
    suspend fun postLogin(@Field("unique_id") userId : String,
                          @Field("password") password : String) : Response<LoginModel>

    @GET("subscription")
    suspend fun getSubscription(@Header("Authorization") token : String ) : Response<SubscriptionMOdel>

    @GET("category")
    suspend fun getHomeCategory() : Response<HomeCategoryMOdel>

    @GET("home/{id}")
    suspend fun getHomeData(@Header("Authorization") token : String,@Path("id") id : Int) : Response<HomeStatus>

    @GET("profile")
    suspend fun getProfileData(@Header("Authorization") token : String) : Response<ProfileStatusMOdel>

    @POST("customer-subscription")
    suspend fun postCustomerSub(@Header("Authorization") token : String,
                                @Field("id") id: Int,@Field("sub_days") days : Int,@Field("sub_amount") amount : Int) : Response<CustomerSubscription>
}
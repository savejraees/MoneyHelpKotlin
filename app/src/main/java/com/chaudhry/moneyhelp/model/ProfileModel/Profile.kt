package com.chaudhry.moneyhelp.model.ProfileModel

data class Profile(
    val days_remaining: Int,
    val email: String,
    val id: Int,
    val mobile: String,
    val name: String,
    val registration_date: String,
    val unique_id: String,
    val validity_date: String
)
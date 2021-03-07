package com.chaudhry.moneyhelp.model.HomeData

data class Service(
    val buy: String,
    val category: Category,
    val category_id: Int,
    val compound: String,
    val date: String,
    val dates: String,
    val description: String,
    val id: Int,
    val interest: String,
    val isactive: Int,
    val result: String,
    val stop_loss: String,
    val target: String,
    val time: String,
    val type: String
)
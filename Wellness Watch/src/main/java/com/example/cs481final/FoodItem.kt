package com.example.cs481final


data class FoodItem(
    val fdcId: Int,
    val description: String,
    val dataType: String,
    val publicationDate: String,
    val ndbNumber: String,
    val foodNutrients: List<FoodNutrient>
)

data class FoodNutrient(
    val number: String,
    val name: String,
    val amount: Double,
    val unitName: String,
    val derivationCode: String?,
    val derivationDescription: String?
)


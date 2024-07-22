package com.example.cs481final

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.cs481final.FoodItem
import retrofit2.http.Headers
import retrofit2.http.Path


public interface FoodAPI {
    @GET("foods/list?=")
    fun getFoodList(@Query("api_key") apiKey: String): Call<List<FoodItem>>
}

/**
public interface FoodAPI {
@Headers(
    value =
    ["X-RapidAPI-Key: 0e81213dd3msh4b3d5caa2798277p1f3b40jsne8d0d155051f",
        "X-RapidAPI-Host: food-calories1.p.rapidapi.com"]
)
@GET("categories/all")
fun getFoodList(): Call<List<FoodCategory>>
}
        */
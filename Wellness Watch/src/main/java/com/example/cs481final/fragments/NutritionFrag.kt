package com.example.cs481final.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.cs481final.R
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.cs481final.FoodAPI
import com.example.cs481final.FoodItem
import com.example.cs481final.fragments.subfrags.RVAdapter
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NutritionFrag : Fragment(R.layout.fragment_nutrition) {


    private val retrofitBuilder by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.nal.usda.gov/fdc/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(FoodAPI::class.java)
    }


    //override fun onCreate(savedInstanceState: Bundle?) {
    //  super.onCreate(savedInstanceState)

    // }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val apiKey = "5QcLocpeXEsHTshMHqfyfDIbtImVgn1hOBTDV8ms"
        val foodData = retrofitBuilder.getFoodList(apiKey)
        //val foodData = retrofitBuilder.getFoodList()

        val view = inflater.inflate(R.layout.fragment_nutrition, container, false)

        foodData.enqueue(object : Callback<List<FoodItem>?> {
       // foodData.enqueue(object : Callback<List<FoodCategory>?> {
            override fun onResponse(
                call: Call<List<FoodItem>?>,
                response: Response<List<FoodItem>?>
            ) {
                if (response.isSuccessful) {
                    val responseReturn = response.body()!!
                    // Log response body
                    Log.d("Retrofit", "Response Body: $responseReturn")

                    val recyclerView: RecyclerView = view.findViewById(R.id.recyclerview)
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())

                    recyclerView.adapter = RVAdapter(responseReturn){ item ->
                        val dialogFragment = Nutritiondetails()
                        Log.d("nutrients",item.foodNutrients.toString() )
                        val bundle = Bundle()
                        bundle.putString("detail",item.foodNutrients.toString())
                        dialogFragment.arguments=bundle
                        dialogFragment.show(requireFragmentManager(), "Nutritiondetails")
                    }

                } else {
                    Log.e("Retrofit", "Unsuccessful response: ${response.code()}")

                }

            }
            override fun onFailure(call: Call<List<FoodItem>?>, t: Throwable){
                Log.d("APIFAIL", "message" +t.message)
            }
        })

        return view
    }



}
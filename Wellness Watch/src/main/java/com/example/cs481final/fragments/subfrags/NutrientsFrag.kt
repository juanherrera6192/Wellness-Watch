package com.example.cs481final.fragments.subfrags

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cs481final.FoodAPI
import com.example.cs481final.FoodItem
import com.example.cs481final.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class NutrientsFrag : Fragment() {

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

        val view = inflater.inflate(R.layout.fragment_nutrients, container, false)

        foodData.enqueue(object : Callback<List<FoodItem>?> {
            override fun onResponse(
                call: Call<List<FoodItem>?>,
                response: Response<List<FoodItem>?>
            ) {
                if (response.isSuccessful) {
                    val responseReturn = response.body()!!
                    // Log response body
                    Log.d("Retrofit", "Response Body: $responseReturn")

                    // Handle response data
                    val recyclerView: RecyclerView = view.findViewById(R.id.recyclerview)
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())

                   // recyclerView.adapter = RVAdapter(responseReturn)

                } else {
                    Log.e("Retrofit", "Unsuccessful response: ${response.code()}")
                    // Handle unsuccessful response
                    // ...
                }

            }
            override fun onFailure(call: Call<List<FoodItem>?>, t: Throwable){
                Log.d("APIFAIL", "message" +t.message)
            }
        })

        return view
    }


}
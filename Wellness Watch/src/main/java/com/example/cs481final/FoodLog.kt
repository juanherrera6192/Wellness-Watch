package com.example.cs481final

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cs481final.fragments.FitnessFrag
import com.example.cs481final.fragments.HealthFrag
import com.example.cs481final.fragments.LogMealFrag
import com.example.cs481final.fragments.NutritionFrag
import com.example.cs481final.fragments.ProfileFrag
import com.google.android.material.bottomnavigation.BottomNavigationView

class FoodLog : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_log)

        val bundle: Bundle? = intent.extras
        val email: String? = intent.getStringExtra("email")
        Log.d("email", email.toString())

        val bundleFrag = Bundle()
        bundleFrag.putString("email", email)

        val logFrag = LogMealFrag()
        logFrag.arguments= bundleFrag
        val nutritionFrag = NutritionFrag()
        val fitFrag = FitnessFrag()
        val profileFrag = ProfileFrag()
        val healthFrag = HealthFrag()


        fitFrag.arguments= bundleFrag
        profileFrag.arguments= bundleFrag
        healthFrag.arguments= bundleFrag

        changeFragment(logFrag)

        findViewById<BottomNavigationView>(R.id.bottom_nav).setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.logTab -> {
                    changeFragment(logFrag)
                    true
                }
                R.id.nutritionTab -> {
                    changeFragment(nutritionFrag)
                    true
                }
                R.id.workoutTab -> {
                    changeFragment(fitFrag)
                    true
                }
                R.id.healthTab -> {
                    changeFragment(healthFrag)
                    true
                }
                R.id.profileTab -> {
                    changeFragment(profileFrag)
                    true
                }
                else -> false
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, fragment)
            commit()
        }
    }
}
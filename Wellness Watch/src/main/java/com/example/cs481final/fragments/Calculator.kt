package com.example.cs481final.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.example.cs481final.R

class Calculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        findViewById<Button>(R.id.backButton).setOnClickListener{
            finish()
        }

        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)
        buttonSubmit.setOnClickListener {
            val height = findViewById<EditText>(R.id.editTextHeight).text.toString()
            val currentWeight = findViewById<EditText>(R.id.editTextWeight).text.toString()
            val weightGoal = findViewById<EditText>(R.id.editTextWeightGoal).text.toString()
            val age = findViewById<EditText>(R.id.editTextAge).text.toString()


            val spinnerGender = findViewById<Spinner>(R.id.spinnerGender)
            val selectedGender = spinnerGender.selectedItem.toString()

            val heightNo = height.toDoubleOrNull() ?: return@setOnClickListener
            val ageNo = age.toIntOrNull() ?: return@setOnClickListener
            val currentWeightNo = currentWeight.toDoubleOrNull() ?: return@setOnClickListener
            val weightGoalNo = weightGoal.toDoubleOrNull() ?: return@setOnClickListener

            val suggestedCalories =
                calculateSuggestedCalories(heightNo, currentWeightNo, ageNo, weightGoalNo, selectedGender)

            val textViewSuggestedCalories =
                findViewById<TextView>(R.id.textViewSuggestedCalories)
            textViewSuggestedCalories.text = "Suggested Daily Calories: $suggestedCalories"
        }

    }
    fun calculateSuggestedCalories(
        height: Double,
        currentWeight: Double,
        age: Int,
        weightGoal: Double,
        gender: String
    ): Int {
        var suggestedCalories: Double = 2000.0
        if (gender == "Female") {
            var meta =
                447.593 + (9.563 * currentWeight * 0.45) + (3.098 * height * 2.54) - (4.33 * age)

                suggestedCalories = (currentWeight - weightGoal) * 0.45 * 7700 / 150 + meta


        } else {
            var meta =
                88.362 + (13.75 * currentWeight * 0.45) + (5.003 * height * 2.54) - (5.677 * age)

                suggestedCalories = (currentWeight - weightGoal) * 0.45 * 7700 / 150 + meta

        }

          val IntCalorie = suggestedCalories.toInt()
        return IntCalorie
    }
}
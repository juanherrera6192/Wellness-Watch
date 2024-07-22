package com.example.cs481final.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.cs481final.FoodItem
import com.example.cs481final.R


class Nutritiondetails : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_details_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val container = view.findViewById<LinearLayout>(R.id.container)

        arguments?.getString("detail")?.split(",")?.forEach { nutrient ->
            val nutrientParts = nutrient.split("=")
            if (nutrientParts.size == 2) { // Ensure it has key-value format
                val key = nutrientParts[0].trim()
                val value = nutrientParts[1].trim()

                val textView = TextView(requireContext()).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    if(key != "derivationDescription" &&
                        key != "derivationCode" && key != "[FoodNutrient(number"  && key != "FoodNutrient(number"){

                    text = "$key: $value"
                    textSize = 16f
                    setPadding(0, 4, 0, 4)

                }

                }
                container.addView(textView)
            }
        }

    }

    companion object {
        const val TAG = "NutritiondetailsDialog"
    }
}
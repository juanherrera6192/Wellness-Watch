package com.example.cs481final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.cs481final.fragments.NutritionFrag
import com.example.cs481final.fragments.ProfileFrag
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore

class EditProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        findViewById<Button>(R.id.backButton).setOnClickListener{
            finish()
        }

        findViewById<Button>(R.id.infoButton).setOnClickListener {
            val height = findViewById<TextInputEditText>(R.id.height).text.toString()
            val weight = findViewById<TextInputEditText>(R.id.weight).text.toString()
            val gender = findViewById<TextInputEditText>(R.id.gender).text.toString()
            val age = findViewById<TextInputEditText>(R.id.age).text.toString()
            val db = FirebaseFirestore.getInstance()
            val user: MutableMap<String, Any> = HashMap()
            user["height"] = height
            user["weight"] = weight
            user["gender"] = gender
            user["age"] = age

            db.collection("users")
                .add(user)
                .addOnSuccessListener {
                    Log.d("dbfirebase", "save: ${user}")
                }
                .addOnFailureListener {
                    Log.d("dbfirebase Failed", "${user}")
                }
            db.collection("users")
                .get()
                .addOnCompleteListener {
                    val result: StringBuffer = StringBuffer()
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Account Created!", Toast.LENGTH_SHORT).show()
                        for (document in it.result!!) {
                            Log.d(
                                "dbfirebase", "retreive: " +
                                        "${document.data.getValue("height")} " +
                                        "${document.data.getValue("weight")} "
                            )

                        }


                    } else {
                        Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}
package com.example.cs481final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.index.IndexEntry

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        findViewById<Button>(R.id.CreateAccount).setOnClickListener {
            val email = findViewById<TextInputEditText>(R.id.textEmail).text.toString()
            val password = findViewById<TextInputEditText>(R.id.textPassword).text.toString()
            val confirmPassword = findViewById<TextInputEditText>(R.id.textConfirmPassword).text. toString()
            val firstName = findViewById<TextInputEditText>(R.id.textFirstName).text.toString()
            val lastName = findViewById<TextInputEditText>(R.id.textLastName).text.toString()
            val db = FirebaseFirestore.getInstance()
            val user: MutableMap<String, Any> = HashMap()
            user["email"] = email
            user["password"] = password
            user["confirmPassword"] = confirmPassword
            user["firstName"] = firstName
            user["lastName"] = lastName



            if (password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {

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
                                    Log.d("dbfirebase", "retreive: " +
                                            "${document.data.getValue("email")} " +
                                            "${document.data.getValue("password")} ")

                                }
                                // directs back to log in page
                                startActivity(Intent(this, MainActivity::class.java))
                            }
                            else {
                                Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show()
                            }
                        }

                } else {
                    Toast.makeText(this, "Error: Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            }
        }

        findViewById<Button>(R.id.backButton).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
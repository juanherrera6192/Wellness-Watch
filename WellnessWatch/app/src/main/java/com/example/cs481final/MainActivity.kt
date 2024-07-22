package com.example.cs481final

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.cs481final.fragments.HealthFrag
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.login).setOnClickListener {
            val email = findViewById<TextInputEditText>(R.id.textEmail).text.toString()
            val password = findViewById<TextInputEditText>(R.id.textPassword).text.toString()
            val db = FirebaseFirestore.getInstance()
            val user: MutableMap<String, Any> = HashMap()
            user["email"] = email
            user["password"] = password

            db.collection("users")
                .get()
                .addOnCompleteListener {
                    val result: StringBuffer = StringBuffer()
                    if (it.isSuccessful) {
                        for (document in it.result!!) {
                            // matching input with database values
                            if(email == "${document.data.getValue("email")}" && password == "${document.data.getValue("password")}") {
                                // directs to notifications/food log page
                                val intent = Intent(this, FoodLog::class.java)
                                intent.putExtra("email", email)
                                if (ActivityCompat.checkSelfPermission(
                                        this,
                                        Manifest.permission.POST_NOTIFICATIONS
                                    ) != PackageManager.PERMISSION_GRANTED
                                ){
                                    startActivity(Intent(this, Notifications::class.java))
                                    Toast.makeText(this, "Logged In!", Toast.LENGTH_SHORT).show()
                                }
                                else{
                                    startActivity(Intent(this, FoodLog::class.java))
                                    Toast.makeText(this, "Logged In!", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }

        }

        // directs to sign up page
        findViewById<Button>(R.id.signUp).setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }

        //for permissions
        findViewById<Button>(R.id.bPermission).setOnClickListener {
            permissionRequest()
        }

    }
    private fun permissionRequest(){
        var permissionList = mutableListOf<String>()
        if (!(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)){
            permissionList.add(android.Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (!(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            permissionList.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (permissionList.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList.toTypedArray(),100)
        }
    }

override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            100 -> {
                for (index in grantResults.indices){
                    if (grantResults[index] == PackageManager.PERMISSION_GRANTED){
                        Log.d("CS481Permission", "Your ${permissions[index]} successfully granted")
                    }
                }
            }
        }
    }
}
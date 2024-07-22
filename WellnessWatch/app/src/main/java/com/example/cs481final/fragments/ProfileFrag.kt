package com.example.cs481final.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.cs481final.EditProfile
import com.example.cs481final.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.util.UUID


class ProfileFrag : Fragment() {

    val db = FirebaseFirestore.getInstance()
    val storage = FirebaseStorage.getInstance()
    var storageRef = storage.reference
    lateinit var profileImg : ImageView
    lateinit var imageUri : Uri
    lateinit var uploadTask: UploadTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        profileImg = view.findViewById<ImageView>(R.id.profilePic)

        val calculator = view.findViewById<TextView>(R.id.CalCalc)
        calculator.setOnClickListener(){
            val intent = Intent(requireContext(), Calculator::class.java)
            startActivity(intent)
        }

        profileImg.setOnClickListener(){
            choosePicture()
        }
        val email = arguments?.getString("email")
        //view.findViewById<TextView>(R.id.tvEmail).text = email.toString()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editProfile: TextView = view.findViewById(R.id.EditProf)

        editProfile.setOnClickListener{
            val intent = Intent(activity, EditProfile::class.java)

            startActivity(intent)


        }
    }

    private val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {

            val data: Intent? = result.data
            data?.data?.let { uri ->
                imageUri = uri
                profileImg.setImageURI(imageUri)
                uploadPicture()
            }

        }
    }

    private fun choosePicture() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        getContent.launch(intent)
    }

    private fun uploadPicture(){


        val randomKey = UUID.randomUUID().toString()
        val riversRef = storageRef.child("images/$randomKey")
        uploadTask = riversRef.putFile(imageUri)

// Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener {
            Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
        }.addOnSuccessListener { taskSnapshot ->
            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
        }

    }

}
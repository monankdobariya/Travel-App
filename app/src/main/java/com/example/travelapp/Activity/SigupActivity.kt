package com.example.travelapp.Activity

import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.travelapp.congratulationActivity
import com.example.travelapp.databinding.ActivitySigupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.util.UUID

class SigupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySigupBinding
    private lateinit var auth: FirebaseAuth
    var PICK_IMAGE_REQUEST = 100
    lateinit var storageReference: StorageReference
    lateinit var images: String
    lateinit var filePath: Uri
    lateinit var bitmap: Bitmap
    lateinit var sharedPreferences: SharedPreferences
    lateinit var firebaseDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        firebaseDatabase = FirebaseDatabase.getInstance().getReference()

        storageReference = FirebaseStorage.getInstance().reference
        workingClass()
    }


    private fun workingClass() {

        binding.imgUser.setOnClickListener {
            getImage()
        }

        binding.btnCreateAccount.setOnClickListener {
            var name = binding.edtName.text.toString()
            var email = binding.edtEmail.text.toString()
            var password = binding.edtPassword.text.toString()

            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            } else if (email.isEmpty()) {
                Toast.makeText(this, "please enter your email", Toast.LENGTH_SHORT).show()
            } else {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "created account", Toast.LENGTH_SHORT).show()
                        var myEdit: SharedPreferences.Editor = sharedPreferences.edit()
                        myEdit.putBoolean("isLogin", true)
                        myEdit.commit()
                        var intent = Intent(this, congratulationActivity::class.java)
                        startActivity(intent)
                        finish()
                        addUserData(
                            name,
                            email,
                            images,
                            auth.currentUser?.uid!!
                        )

                    }
                }.addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }

            }

        }
    }

    private fun addUserData(
        name: String,
        email: String,
        images: String,
        uid: String
    ) {

        firebaseDatabase.child("user").child(uid)
            .setValue(userModelClass(name, email, images, uid))
    }

    fun getImage() {
        binding.imgUser.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Image from here..."),
                PICK_IMAGE_REQUEST
            )

        }
    }

    private fun uploadImagetoFirebase() {
        if (filePath != null) {
            // Code for showing progressDialog while uploading
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading...")
            progressDialog.show()
            // Defining the child of storageReference
            val ref: StorageReference =
                storageReference.child("user_images/" + UUID.randomUUID().toString())
            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                .addOnCompleteListener {
//                it.result.uploadSessionUri
                    ref.downloadUrl.addOnSuccessListener { images = it.toString() }
                }
                .addOnSuccessListener { // Image uploaded successfully
                    // Dismiss dialog

                    progressDialog.dismiss()

                    Toast.makeText(this, "Image Uploaded!!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e -> // Error, Image not uploaded
                    progressDialog.dismiss()
                    Toast.makeText(this, "Failed " + e.message, Toast.LENGTH_SHORT).show()
                }
                .addOnProgressListener { taskSnapshot ->
                    // Progress Listener for loading
                    // percentage on the dialog box
                    val progress = (100.0
                            * taskSnapshot.bytesTransferred
                            / taskSnapshot.totalByteCount)
                    progressDialog.setMessage(
                        "Uploaded " + progress.toInt() + "%"
                    )
                }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if (requestCode === PICK_IMAGE_REQUEST && resultCode === RESULT_OK && data != null && data.getData() != null) {

                // Get the Uri of data
                filePath = data.getData()!!
                try {
                    uploadImagetoFirebase()
                    bitmap = MediaStore.Images.Media
                        .getBitmap(contentResolver, filePath)
                    binding.imgUser.setImageBitmap(bitmap)
                } catch (e: IOException) {
                    // Log the exception
                    e.printStackTrace()
                }
            }
        }
    }

}

class userModelClass(
    var name: String,
    var address: String,
    var email: String,
    var uid: String?

)





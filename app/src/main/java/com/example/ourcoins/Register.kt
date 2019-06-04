package com.example.ourcoins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {


    lateinit var mAuth: FirebaseAuth
    lateinit var  database : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        database= FirebaseFirestore.getInstance()
        mAuth = FirebaseAuth.getInstance();


        RLoginButton.setOnClickListener {

            val loginIntent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(loginIntent)

        }

        RRegisterButton.setOnClickListener {
            val email = REmail.text.toString().trim()
            val password = RPassword.text.toString().trim()
            val name = RName.text.toString().trim()


            if (TextUtils.isEmpty(email)) {

                REmail.error = "Enter Email"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {

                RPassword.error = "Enter Password"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(name)) {

                RName.error = "Enter Name"
                return@setOnClickListener
            }

            createUser(email, password, name)
        }
    }
    fun createUser( email: String, password: String, name: String) {

        progressBar.visibility= View.VISIBLE

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isComplete) {

                    val user: FirebaseUser? =mAuth.currentUser

                    val OUser= User(user!!.uid, name, email).toMap()

                    database!!.collection("users")
                        .add(OUser)
                        .addOnSuccessListener { documentReference ->
                            val Intent = Intent(applicationContext, LoginActivity::class.java)
                            startActivity(Intent)
                            finish()
                            progressBar.visibility=View.GONE
                        }
                        .addOnFailureListener { e ->

                            Toast.makeText(applicationContext, "¡ERROR!", Toast.LENGTH_SHORT).show()
                        }

                } else {

                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
                    progressBar.visibility=View.GONE

                }
            }
    }
}

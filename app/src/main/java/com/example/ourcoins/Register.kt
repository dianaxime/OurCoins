package com.example.ourcoins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {


    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mAuth = FirebaseAuth.getInstance();


        RLoginButton.setOnClickListener {

            val loginIntent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(loginIntent)

        }

        RRegisterButton.setOnClickListener {
            val email = REmail.text.toString().trim()
            val password = RPassword.text.toString().trim()


            if (TextUtils.isEmpty(email)) {

                REmail.error = "Enter Email"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {

                RPassword.error = "Enter Password"
                return@setOnClickListener
            }

            createUser(email, password)
        }
    }
    fun createUser( email: String, password: String) {

        progressBar.visibility= View.VISIBLE

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val Intent = Intent(applicationContext, HomeActivity::class.java)
                    startActivity(Intent)
                    finish()
                    progressBar.visibility=View.GONE

                } else {

                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
                    progressBar.visibility=View.GONE

                }
            }
    }
}

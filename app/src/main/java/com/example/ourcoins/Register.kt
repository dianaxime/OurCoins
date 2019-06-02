package com.example.ourcoins

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    lateinit var mProgressbar : ProgressDialog
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mAuth = FirebaseAuth.getInstance();
        mProgressbar = ProgressDialog(this)

        RRegisterButton.setOnClickListener {

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

        mProgressbar.setMessage("Please wait..")
        mProgressbar.show()


        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val Intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(Intent)
                    finish()
                    mProgressbar.dismiss()

                } else {

                    Toast.makeText(this, "Authentication failed.${task.exception}", Toast.LENGTH_SHORT).show()
                    mProgressbar.dismiss()

                }
            }
    }
}

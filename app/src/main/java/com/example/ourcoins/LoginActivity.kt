package com.example.ourcoins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    lateinit var  mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth= FirebaseAuth.getInstance();
        LoginButton.setOnClickListener {
            val email = Email.text.toString().trim()
            val password = Password.text.toString().trim()

            if(TextUtils.isEmpty(email)){
                Email.error = " Enter Email"
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(password)){
                Password.error = " Enter Password"
                return@setOnClickListener
            }

            loginUser(email , password)
        }

        RegisterButton.setOnClickListener {
            val registerActivity = Intent(applicationContext , Register::class.java)
            startActivity(registerActivity)
            finish()
        }
    }
    private fun loginUser(email: String, password: String) {
        progressBar2.visibility= View.VISIBLE

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val startIntent  = Intent(applicationContext , HomeActivity::class.java)
                    startActivity(startIntent)
                    finish()
                    progressBar2.visibility= View.GONE
                } else {

                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
                    progressBar2.visibility= View.GONE
                }
            }
    }
}

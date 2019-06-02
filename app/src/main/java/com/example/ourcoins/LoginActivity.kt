package com.example.ourcoins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    lateinit var  mAuth: FirebaseAuth
    lateinit var mProgressbar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth= FirebaseAuth.getInstance()
    }
    fun Login(view: View) {
        val email = Email.text.toString().trim()
        val password = Password.text.toString().trim()

        if(TextUtils.isEmpty(email)){
            Email.error = " Enter Email"
        }

        if(TextUtils.isEmpty(password)){
            Email.error = " Enter Password"
        }

        loginUser(email , password)
    }

    fun Register (view: View){
        val registerActivity = Intent(applicationContext , Register::class.java)
        startActivity(registerActivity)
        finish()
    }
    private fun loginUser(email: String, password: String) {
        mProgressbar.visibility= View.VISIBLE

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val startIntent  = Intent(applicationContext , MainActivity::class.java)
                    startActivity(startIntent)
                    finish()
                    mProgressbar.visibility= View.GONE
                } else {

                    Toast.makeText(this, "Authentication failed.${task.exception}", Toast.LENGTH_SHORT).show()
                    mProgressbar.visibility= View.GONE
                }
            }
    }
}

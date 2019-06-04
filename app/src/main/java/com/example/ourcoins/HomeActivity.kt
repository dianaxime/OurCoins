package com.example.ourcoins

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.nav_header_home.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        loadUserInfo()
        setSupportActionBar(toolbar)

        mAuth= FirebaseAuth.getInstance()

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

    }

    override fun onStart() {
        super.onStart()

        val currentUser = FirebaseAuth.getInstance().currentUser
        if(currentUser == null){

            val intent = Intent(applicationContext , LoginActivity::class.java)
            startActivity(intent)
            finish()

        }else{
            //loadUserInfo()
            Toast.makeText(applicationContext , "Login Successfully" , Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.Movements -> {
                val startIntent  = Intent(applicationContext , MainActivity::class.java)
                startActivity(startIntent)
            }
            R.id.Account -> {
                val startIntent  = Intent(applicationContext , AccountActivity::class.java)
                startActivity(startIntent)
            }
            R.id.Statistics -> {
                val startIntent  = Intent(applicationContext , StatisticsActivity::class.java)
                startActivity(startIntent)
            }
            R.id.Enter_expenses -> {
                val startIntent  = Intent(applicationContext , ExpensesActivity::class.java)
                startActivity(startIntent)
            }
            R.id.Enter_income -> {
                val startIntent  = Intent(applicationContext , IncomesActivity::class.java)
                startActivity(startIntent)
            }
            R.id.Groups -> {
                val startIntent  = Intent(applicationContext , GroupsActivity::class.java)
                startActivity(startIntent)
            }
            R.id.Balance -> {
                val startIntent  = Intent(applicationContext , BalanceActivity::class.java)
                startActivity(startIntent)
            }
            R.id.About -> {
                val startIntent  = Intent(applicationContext , AboutActivity::class.java)
                startActivity(startIntent)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun loadUserInfo(){
        val user = FirebaseAuth.getInstance().currentUser
        val us = FirebaseFirestore.getInstance()
        us.collection("users").get().addOnSuccessListener { OnSuccessListener<QuerySnapshot> {
            Log.e("SHOW",it.toObjects(User::class.java).size.toString())

        }
        }
        getuserinfo(us)
        if (user != null) {
            if (user.displayName!=null) {

                var nombre = user.displayName
                Username.text = nombre
            }
        }
    }
    fun getuserinfo(mFirebaseFirestore: FirebaseFirestore) {
        val TAG = "SS"
        val correo = intent.getStringExtra("CORREO")
        mFirebaseFirestore.collection("users").whereEqualTo("correo", correo).get()
            .addOnSuccessListener(OnSuccessListener { documentSnapshots ->
                if (documentSnapshots.isEmpty) {
                    Log.e(TAG, "onSuccess: LIST EMPTY")
                    return@OnSuccessListener
                } else {
                    val types = documentSnapshots.toObjects(User::class.java)

                    Username.text = types[0].nombre!!
                    Useremail.text=types[0].correo!!
                    Log.e(TAG, "onSuccess: " + types[0].nombre!!)
                }
            })
        mAuth= FirebaseAuth.getInstance()


    }

}

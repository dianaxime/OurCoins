package com.example.ourcoins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.activity_expenses.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.nav_header_home.*

class ExpensesActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expenses)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawerexpenses, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerexpenses.addDrawerListener(toggle)
        toggle.syncState()

        navexpenses.setNavigationItemSelectedListener(this)


        //GetInfo()
    }

    fun guardarReceta(view: View){
        val correo = intent.getStringExtra("CORREO")
        val us = FirebaseFirestore.getInstance()
        us.collection("users").get().addOnSuccessListener { OnSuccessListener<QuerySnapshot>()
        {
            Log.e("SHOW",it.toObjects(User::class.java).size.toString())

        }}

        us.collection("users").whereEqualTo("correo", correo).get()
            .addOnSuccessListener(OnSuccessListener { documentSnapshots ->
                if (documentSnapshots.isEmpty) {

                    return@OnSuccessListener
                } else {
                    documentSnapshots.documents.get(0)
                    val types = documentSnapshots.toObjects(User::class.java)
                    types[0].gastos!!.add(Gastos(editText6.text.toString(), spinner.text.toString(), editText7.text.toString(), textView13.text.toString(), editText5.text.toString(), spinner2.text.toString()))
                    Log.e("oooo", "onSuccess: " + types[0].gastos!!.size!!+" "+ documentSnapshots.documents.get(0).id)
                    //val id=types[0].id

                    auth= FirebaseAuth.getInstance()
                    val frankDocRef = us.collection("users").document(documentSnapshots.documents.get(0).id).update("gastos",types[0].gastos)
                    //val frankDocRef = us.collection("users").document(documentSnapshots.documents.get(0).id).delete("recetas",types[0].recetas)

                }
            }).addOnCompleteListener(OnCompleteListener { task->
                if (task.isSuccessful()) {
                    for (f in task.getResult()!!.getDocuments()) {
                        // here you can get the id.
                        val client = f.toObject(User::class.java)//(f.getId())
                        // you can apply your actions...
                        Log.e("COMPLETE",f.id)
                    }
                    action()
                }
            })


    }
    private fun action(){
        val correo = intent.getStringExtra("CORREO")
        val intent = Intent(applicationContext, HomeActivity::class.java)
        intent.putExtra("CORREO", correo)
        startActivityForResult(intent, 1)
        finish()
    }
    override fun onBackPressed() {
        if (drawerexpenses.isDrawerOpen(GravityCompat.START)) {
            drawerexpenses.closeDrawer(GravityCompat.START)
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
                startIntent.putExtra("CORREO", Useremail.text.toString())
                startActivity(startIntent)
                this.finish()
            }
            R.id.Account -> {
                val startIntent  = Intent(applicationContext , AccountActivity::class.java)
                startIntent.putExtra("CORREO", Useremail.text.toString())
                startIntent.putExtra("NOMBRE", Username.text.toString())
                startActivity(startIntent)
                this.finish()
            }
            R.id.Statistics -> {
                val startIntent  = Intent(applicationContext , StatisticsActivity::class.java)
                startIntent.putExtra("CORREO", Useremail.text.toString())
                startIntent.putExtra("NOMBRE", Username.text.toString())
                startActivity(startIntent)
                this.finish()
            }
            R.id.Enter_expenses -> {
                val startIntent  = Intent(applicationContext , ExpensesActivity::class.java)
                startIntent.putExtra("CORREO", Useremail.text.toString())
                startIntent.putExtra("NOMBRE", Username.text.toString())
                startActivity(startIntent)
                this.finish()
            }
            R.id.Enter_income -> {
                val startIntent  = Intent(applicationContext , IncomesActivity::class.java)
                startIntent.putExtra("CORREO", Useremail.text.toString())
                startIntent.putExtra("NOMBRE", Username.text.toString())
                startActivity(startIntent)
                this.finish()
            }
            R.id.Groups -> {
                val startIntent  = Intent(applicationContext , GroupsActivity::class.java)
                startIntent.putExtra("CORREO", Useremail.text.toString())
                startIntent.putExtra("NOMBRE", Username.text.toString())
                startActivity(startIntent)
                this.finish()
            }
            R.id.Balance -> {
                val startIntent  = Intent(applicationContext , BalanceActivity::class.java)
                startIntent.putExtra("CORREO", Useremail.text.toString())
                startIntent.putExtra("NOMBRE", Username.text.toString())
                startActivity(startIntent)
                this.finish()
            }
            R.id.About -> {
                val startIntent  = Intent(applicationContext , AboutActivity::class.java)
                startIntent.putExtra("CORREO", Useremail.text.toString())
                startIntent.putExtra("NOMBRE", Username.text.toString())
                startActivity(startIntent)
                this.finish()
            }
        }

        drawerexpenses.closeDrawer(GravityCompat.START)
        return true
    }

    fun GetInfo(){
        var email = intent.getStringExtra("CORREO")
        var name = intent.getStringExtra("NOMBRE")
        Useremail.text=email!!
        Username.text=name!!
    }
}

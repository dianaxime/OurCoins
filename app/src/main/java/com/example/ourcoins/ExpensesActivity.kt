package com.example.ourcoins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_expenses.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.nav_header_home.*

class ExpensesActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

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

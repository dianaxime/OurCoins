package com.example.ourcoins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_account.*
import kotlinx.android.synthetic.main.app_bar_home.*

class AccountActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, draweraccount, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        draweraccount.addDrawerListener(toggle)
        toggle.syncState()

        navaccount.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (draweraccount.isDrawerOpen(GravityCompat.START)) {
            draweraccount.closeDrawer(GravityCompat.START)
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
                val startIntent  = Intent(applicationContext , HomeActivity::class.java)
                startActivity(startIntent)
                this.finish()
            }
            R.id.Account -> {
                val startIntent  = Intent(applicationContext , AccountActivity::class.java)
                startActivity(startIntent)
                this.finish()
            }
            R.id.Statistics -> {
                val startIntent  = Intent(applicationContext , StatisticsActivity::class.java)
                startActivity(startIntent)
                this.finish()
            }
            R.id.Enter_expenses -> {
                val startIntent  = Intent(applicationContext , ExpensesActivity::class.java)
                startActivity(startIntent)
                this.finish()
            }
            R.id.Enter_income -> {
                val startIntent  = Intent(applicationContext , IncomesActivity::class.java)
                startActivity(startIntent)
                this.finish()
            }
            R.id.Groups -> {
                val startIntent  = Intent(applicationContext , GroupsActivity::class.java)
                startActivity(startIntent)
                this.finish()
            }
            R.id.Balance -> {
                val startIntent  = Intent(applicationContext , BalanceActivity::class.java)
                startActivity(startIntent)
                this.finish()
            }
            R.id.About -> {
                val startIntent  = Intent(applicationContext , AboutActivity::class.java)
                startActivity(startIntent)
                this.finish()
            }
        }

        draweraccount.closeDrawer(GravityCompat.START)
        return true
    }
}

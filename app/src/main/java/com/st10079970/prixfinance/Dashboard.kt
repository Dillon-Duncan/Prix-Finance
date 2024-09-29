package com.st10079970.prixfinance

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class Dashboard : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drwLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var recView: RecyclerView
    private lateinit var adapter: CardAdapter
    private lateinit var tempData: List<CardItems>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        // Setting up the layout manager
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        //  Setting up Recycler Display + Linking the layoutManager
        recView = findViewById(R.id.recViewBudgetDisplay)
        recView.layoutManager = layoutManager
        recView.setHasFixedSize(true)


        //getting userdata
        tempData = arrayListOf()
        getUserData()

        // Set up the toolbar
        val toolbar: Toolbar = findViewById(R.id.toolBarDashboard)
        setSupportActionBar(toolbar)

        // Setting up DrawerLayout and ActionBarDrawerToggle
        drwLayout = findViewById(R.id.drwLayoutDashboard)
        setupDrawerLayout(toolbar)

        // Setting up NavigationView
        navView = findViewById(R.id.navView_dashboard)
        setupNavigationView()

        // Setting up custom overlap display
        recView.addItemDecoration(CardOverlapDecoration())


        // Adjust card elevation and background
        recView.setBackgroundColor(Color.TRANSPARENT)


    }

    //setting up the display and configuration of toolbar
    private fun setupDrawerLayout(toolbar: Toolbar) {
        toggle =  ActionBarDrawerToggle(this, drwLayout, toolbar, R.string.tgl_open, R.string.tgl_close)
        drwLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    //routing the navigation to the correct layout
    private fun setupNavigationView() {

        navView.setNavigationItemSelectedListener {
            drwLayout.closeDrawer(GravityCompat.END)
            when(it.itemId) {
                R.id.itmDashboard -> {
                    intent = Intent(this, Dashboard::class.java)
                    startActivity(intent)
                }
                R.id.itmNotifications -> {
                    intent = Intent(this, Notifications::class.java)
                    startActivity(intent)
                }
                R.id.itmBudgetManagement -> {
                    intent = Intent(this, Budgets::class.java)
                    startActivity(intent)
                }
                R.id.itmFinancialOverview -> {
                    intent = Intent(this, Overview::class.java)
                    startActivity(intent)
                }
                R.id.itmTransactions -> {
                    intent = Intent(this, Transactions::class.java)
                    startActivity(intent)
                }
                R.id.itmGoals -> {
                    intent = Intent(this, Goals::class.java)
                    startActivity(intent)
                }
                R.id.itmSettings -> {
                    intent = Intent(this, Settings::class.java)
                    startActivity(intent)
                }
                R.id.itmLogout -> {
                    //to configure logout
                }
            }
            true
        }
    }

    //setting up the temp userdata
    fun getUserData(): List<CardItems> {
        return listOf(
            CardItems("Groceries", 221270.00, "Caution"),
            CardItems("Rent", 131270.00, "On Track"),
            CardItems("Entertainment", 110396.00, "Caution"),
            CardItems("Transportation", 230902.00, "On Track"),
            CardItems("Utilities", 100210.00, "Caution"),
            CardItems("Savings", 241212.00, "On Track"),
            CardItems("Healthcare", 290522.00, "Caution"),
            CardItems("Clothing", 120804.00, "On Track")
        )

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (toggle.onOptionsItemSelected(item)) true else super.onOptionsItemSelected(item)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

}
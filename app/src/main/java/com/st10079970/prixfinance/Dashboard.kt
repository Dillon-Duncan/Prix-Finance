package com.st10079970.prixfinance

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class Dashboard : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drwLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var recView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        val toolbar: Toolbar = findViewById(R.id.toolBarDashboard)
        setSupportActionBar(toolbar)

        setupDrawerLayout(toolbar)
        setupNavigationView()

        recView = findViewById(R.id.recViewBudgetDisplay)
        recView.layoutManager = LinearLayoutManager(this)
        recView.setHasFixedSize(true)
    }

    private fun setupDrawerLayout(toolbar: Toolbar) {
        drwLayout = findViewById(R.id.drwLayoutDashboard)
        toggle = ActionBarDrawerToggle(this, drwLayout, toolbar, R.string.tgl_open, R.string.tgl_close)
        drwLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setupNavigationView() {
        navView = findViewById(R.id.navView_dashboard)
        navView.setNavigationItemSelectedListener {
            drwLayout.closeDrawer(GravityCompat.END)
            when (it.itemId) {
                R.id.itmDashboard -> startActivity(Intent(this, Dashboard::class.java))
                R.id.itmNotifications -> startActivity(Intent(this, Notifications::class.java))
                R.id.itmBudgetManagement -> startActivity(Intent(this, Budgets::class.java))
                R.id.itmFinancialOverview -> startActivity(Intent(this, Overview::class.java))
                R.id.itmTransactions -> startActivity(Intent(this, Transactions::class.java))
                R.id.itmGoals -> startActivity(Intent(this, Goals::class.java))
                R.id.itmSettings -> startActivity(Intent(this, Settings::class.java))
                R.id.itmLogout -> { /* configure logout */ }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (toggle.onOptionsItemSelected(item)) true else super.onOptionsItemSelected(item)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    private fun setupBudgetsDisplay() {
        recView = findViewById(R.id.recViewBudgetDisplay)
        recView.removeAllViews()

        val itemCount = 3
        for (i in 0 until itemCount) {
            OverlappingBudgetItemView(this).apply {
                setFloater("Food", "On Track", 120804.00)
                recView.addView(this)
            }
        }
    }
}
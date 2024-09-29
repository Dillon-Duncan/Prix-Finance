package com.st10079970.prixfinance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView

class GoalsFragment : Fragment() {

    private lateinit var drwHomeFrag: DrawerLayout
    private lateinit var navHomeFrag: NavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        drwHomeFrag = view.findViewById(R.id.drwHomeFrag)
        navHomeFrag = view.findViewById(R.id.navHomeFrag)

        setupDraw()

        return view
    }

    private fun setupDraw(){
        //routing the navigation clicks to the right frag layout
        navHomeFrag.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.itmDashboard -> navigateTo(R.layout.fragment_home)
                R.id.itmBudgetManagement -> navigateTo(R.layout.fragment_budget_management)
                R.id.itmTransactions -> navigateTo(R.layout.fragment_transactions)
                R.id.itmNotifications -> navigateTo(R.layout.fragment_notifications)
                R.id.itmGoals -> navigateTo(R.layout.fragment_goals)
                R.id.itmSettings -> navigateTo(R.layout.fragment_settings)
                R.id.itmLogout -> navigateTo(R.layout.fragment_home)
            }
            drwHomeFrag.closeDrawer(GravityCompat.END)
            true
        }

    }

    //func that binds the navigation layout and menu navigation item pressed
    private fun navigateTo(destinationID: Int) {
        view?.findNavController()?.navigate(destinationID)
    }



}
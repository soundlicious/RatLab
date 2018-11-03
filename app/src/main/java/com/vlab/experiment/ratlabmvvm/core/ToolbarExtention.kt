package com.vlab.experiment.ratlabmvvm.core

import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController

fun Toolbar.setupWithNavController(
    navController: NavController,
    drawerLayout: DrawerLayout? = null
) {
    androidx.navigation.ui.NavigationUI.setupWithNavController(this, navController, drawerLayout)
}
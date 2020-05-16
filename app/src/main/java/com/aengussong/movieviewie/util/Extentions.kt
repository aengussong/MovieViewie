package com.aengussong.movieviewie.util

import android.content.Context
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.aengussong.movieviewie.R

val Context.isTablet: Boolean
    get() = resources.getBoolean(R.bool.isTablet)

fun DrawerLayout.toggle() {
    val isOpen = isDrawerOpen(GravityCompat.START)
    if (isOpen) {
        closeDrawer(GravityCompat.START)
    } else {
        openDrawer(GravityCompat.START)
    }
}

fun Fragment.getNavigationResult(key: String = "result") =
    findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>(key)

fun Fragment.setNavigationResult(result: Int, key: String = "result") {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, result)
}

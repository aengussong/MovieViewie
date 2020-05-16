package com.aengussong.movieviewie.util

import android.content.Context
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
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
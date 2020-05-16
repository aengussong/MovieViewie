package com.aengussong.movieviewie.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aengussong.movieviewie.R
import com.aengussong.movieviewie.ui.details.DetailsFragment
import com.aengussong.movieviewie.ui.list.ListFragment
import com.aengussong.movieviewie.util.isTablet
import com.aengussong.movieviewie.util.toggle
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: DataViewModel by viewModel()

    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpDrawer()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                //burger displayed only for phones, so no need to check if device is tablet
                drawer_layout.toggle()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Should always be called [onCreate], or [drawerToggle] won't be initialized for sync call in [onPostCreate]
     * */
    private fun setUpDrawer() {
        drawerToggle = ActionBarDrawerToggle(this, drawer_layout, R.string.open, R.string.close)
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        if (!isTablet) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

}

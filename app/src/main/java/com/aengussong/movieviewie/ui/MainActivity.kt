package com.aengussong.movieviewie.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aengussong.movieviewie.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: DataViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

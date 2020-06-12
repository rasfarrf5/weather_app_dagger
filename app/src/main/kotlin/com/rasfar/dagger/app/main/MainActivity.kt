package com.rasfar.dagger.app.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rasfar.dagger.app.R
import dagger.android.AndroidInjection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
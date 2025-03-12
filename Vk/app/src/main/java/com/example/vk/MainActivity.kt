package com.example.vk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vk.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProfileFragment())
                .commit()
        }
    }
}
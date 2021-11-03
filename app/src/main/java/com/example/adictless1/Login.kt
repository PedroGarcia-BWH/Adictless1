package com.example.adictless1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.adictless1.Controlador.Forum
import com.example.adictless1.Controlador.Home
import com.example.adictless1.Controlador.PageController
import com.example.adictless1.Controlador.Progress
import com.google.android.material.tabs.TabLayout

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setUpTabs()
    }

    private fun setUpTabs(){
        val adapter = PageController(supportFragmentManager)
        adapter.addFragment(Home(), "Home")
        adapter.addFragment(Forum(), "Forum")
        adapter.addFragment(Progress(), "Progress")
        val viewpager = findViewById<ViewPager>(R.id.viewpager)
        val tabs = findViewById<TabLayout>(R.id.tabs)
        viewpager.adapter = adapter
        tabs.setupWithViewPager(viewpager)

    }
}
package com.example.adictless1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.ViewPager
import com.example.adictless1.Controlador.Forum
import com.example.adictless1.Controlador.Home
import com.example.adictless1.Controlador.PageController
import com.example.adictless1.Controlador.Progress
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayout

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setUpTabs()
        /*val new = findViewById<CardView>(R.id.newCard)
        if(new.callOnClick()){
            val texto = findViewById<TextView>(R.id.pruebaText)
            texto.setText("Funciona")
        }*/
    }

    private fun setUpTabs(){
        val adapter = PageController(supportFragmentManager)
        adapter.addFragment(Forum(), "Forum")
        adapter.addFragment(Home(), "Home")
        adapter.addFragment(Progress(), "Progress")
        val viewpager = findViewById<ViewPager>(R.id.viewpager)
        val tabs = findViewById<TabLayout>(R.id.tabs)
        viewpager.adapter = adapter
        tabs.setupWithViewPager(viewpager)

    }
}
package com.example.adictless1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.OnClickAction
import android.view.View
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
        /*val anuncio1 = findViewById<CardView>(R.id.announce1)
        anuncio1.setOnClickListener {
            val an1Act = Intent(this, RegisterActivity1::class.java)
            startActivity(an1Act)
        }*/

    }

    private fun setUpTabs(){
        val adapter = PageController(supportFragmentManager)
        adapter.addFragment(Forum(), "Foro")
        adapter.addFragment(Home(), "Inicio")
        adapter.addFragment(Progress(), "Progreso")
        val viewpager = findViewById<ViewPager>(R.id.viewpager)
        val tabs = findViewById<TabLayout>(R.id.tabs)
        viewpager.adapter = adapter
        tabs.setupWithViewPager(viewpager)

    }
}
package com.example.adictless1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.adictless1.Controlador.Forum
import com.example.adictless1.Controlador.Home
import com.example.adictless1.Controlador.PageController
import com.example.adictless1.Controlador.Progress
import com.google.android.material.tabs.TabLayout




class Login : AppCompatActivity() {
    var usuario_intent: CharSequence? =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usuario_intent= intent.extras?.getCharSequence("usuario")
        usuario()
        setUpTabs()
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
    fun usuario(): CharSequence? {
        return usuario_intent
    }
}
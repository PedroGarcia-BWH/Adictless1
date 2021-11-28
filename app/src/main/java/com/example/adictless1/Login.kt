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
    var usuario_intent: String? =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usuario_intent= intent.extras?.getString("usuario")
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

        tabs.getTabAt(0)!!.setIcon(R.drawable.forum_icon)
        tabs.getTabAt(1)!!.setIcon(R.drawable.home_icon)
        tabs.getTabAt(2)!!.setIcon(R.drawable.progress_icon)
        /* Quitar titulo de las pestañas
        tabs.getTabAt(0)!!.setTabLabelVisibility(TabLayout.TAB_LABEL_VISIBILITY_UNLABELED);
        tabs.getTabAt(1)!!.setTabLabelVisibility(TabLayout.TAB_LABEL_VISIBILITY_UNLABELED);
        tabs.getTabAt(2)!!.setTabLabelVisibility(TabLayout.TAB_LABEL_VISIBILITY_UNLABELED);
        */
    }
    fun usuario(): String? {
        return usuario_intent
    }
}
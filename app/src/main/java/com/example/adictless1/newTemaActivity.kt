package com.example.adictless1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import com.example.adictless1.Controlador.Forum
import com.example.adictless1.Controlador.Home
import com.example.adictless1.Controlador.PageController
import com.example.adictless1.Controlador.Progress
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_new_tema.*

class newTemaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_tema)
        var db = Firebase.firestore

        val create = findViewById<Button>(R.id.create)
        create?.setOnClickListener() {
            var temaEnviar = nTema.text.toString()
            var data = hashMapOf("a" to 5)
            db.collection("foro").document(temaEnviar).set(data, SetOptions.merge())
            finish()
        }

        val decline = findViewById<Button>(R.id.cancel)
        decline?.setOnClickListener() {
            finish()
        }
        }
}
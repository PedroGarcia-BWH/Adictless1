package com.example.adictless1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.adictless1.R

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        val newId = getIntent().getExtras()?.getInt("parametro")
        val titulo = findViewById<TextView>(R.id.tituloArticulo)
        val subTitulo= findViewById<TextView>(R.id.subTituloArticulo)
        val cuerpoArticulo = findViewById<TextView>(R.id.cuerpoArticulo)
        val foto = findViewById<ImageView>(R.id.imagenArticulo)

        when(newId)
        {
            1-> {
                titulo.text = getString(R.string.tituloArticulo1)
                subTitulo.text = getString(R.string.subTituloArticulo1)
                cuerpoArticulo.text = getString(R.string.cuerpoArticulo1)
                foto.setImageResource(R.drawable.img_1)
            }
            2->
            {
                titulo.text = getString(R.string.tituloArticulo2)
                subTitulo.text = getString(R.string.subTituloArticulo2)
                cuerpoArticulo.text = getString(R.string.cuerpoArticulo2)
                foto.setImageResource(R.drawable.img_2)
            }
            else->{}
        }
    }
}
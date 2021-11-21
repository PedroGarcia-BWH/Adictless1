package com.example.adictless1.Controlador

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.adictless1.Login
import com.example.adictless1.NewsActivity
import com.example.adictless1.R
import com.example.adictless1.SettingsActivity

//import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.fragment_home.*


/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val an1CardView = view?.findViewById<CardView>(R.id.announce1)
        Log.d("View", an1CardView.toString())
        an1CardView?.setOnClickListener {
            val an1Ac = Intent(activity, NewsActivity::class.java)
            an1Ac.putExtra("parametro", 1);

            activity?.startActivity(an1Ac)
        }


        val an2CardView = view?.findViewById<CardView>(R.id.announce2)
        an2CardView?.setOnClickListener {
            val an2Ac = Intent(activity, NewsActivity::class.java)
            an2Ac.putExtra("parametro", 2);

            activity?.startActivity(an2Ac)
        }
        val activity: Login? = activity as Login?
        val usuario: CharSequence? = activity?.usuario()

        val login_usuario = view?.findViewById<TextView>(R.id.alias)
        login_usuario?.text = "Bienvenido\n" + usuario


        val settings =  view?.findViewById<ImageButton>(R.id.settings)
        settings?.setOnClickListener {
            val settingsAct = Intent(activity, SettingsActivity::class.java)
            activity?.startActivity(settingsAct)
        }
    }

}
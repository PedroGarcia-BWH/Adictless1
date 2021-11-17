package com.example.adictless1.Controlador

import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.adictless1.NewsActivity
import com.example.adictless1.R
import com.example.adictless1.RegisterActivity1


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
        an1CardView?.setOnClickListener{
            Log.d("myTag", "This is my message");
            val an1Ac = Intent(activity, NewsActivity::class.java)
            activity?.startActivity(an1Ac)
        }
    }


}
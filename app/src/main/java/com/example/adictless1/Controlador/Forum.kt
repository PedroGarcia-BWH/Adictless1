package com.example.adictless1.Controlador

import android.os.Bundle
import com.example.adictless1.Controlador.Forum
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.adictless1.R

/**
 * A simple [Fragment] subclass.
 * Use the [Forum.newInstance] factory method to
 * create an instance of this fragment.
 */
class Forum : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forum, container, false)
    }


}
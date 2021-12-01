package com.example.adictless1.Controlador

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.adictless1.Controlador.Forum
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import com.example.adictless1.ChatActivity
import com.example.adictless1.NewsActivity
import com.example.adictless1.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass.
 * Use the [Forum.newInstance] factory method to
 * create an instance of this fragment.
 */
class Forum : Fragment() {

    val db = FirebaseFirestore.getInstance()
    private lateinit var auth: FirebaseAuth

    companion object{
        var TAG = "DocSnippets"
    }
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        auth = Firebase.auth


        val user = auth.currentUser
        val doc_ref = user?.let { db.collection("users").document(it.uid) }

        doc_ref!!.get()
            .addOnSuccessListener { document ->
                if (document.data != null) {
                    Log.d(Forum.TAG, "Datos Recibidos desde la Base de Datos")
                    val textoSesion = view?.findViewById<TextView>(R.id.SOON)
                    textoSesion!!.visibility = View.GONE

                    val candado = view?.findViewById<ImageView>(R.id.Candado)
                    candado!!.visibility = View.GONE
                } else {
                    Log.d(Forum.TAG, "No existe dicho documento en la Base de Datos")
                    val usuario = "Invitado"

                    val compForo = view?.findViewById<Group>(R.id.foro)
                    compForo!!.visibility = View.GONE
                }
            }
            .addOnFailureListener { exception ->
                Log.d(Forum.TAG, "get failed with ", exception)
            }

        val chat = view?.findViewById<CardView>(R.id.fRedesSociales)
        chat?.setOnClickListener(){
            val chatIntent = Intent(activity, ChatActivity::class.java)
            chatIntent.putExtra("nombreTema", "Redes Sociales");
            activity?.startActivity(chatIntent)
        }
        val adding = view?.findViewById<FloatingActionButton>(R.id.addTag)
        adding?.setOnClickListener(){

            val builder = AlertDialog.Builder(getActivity())
            builder.setView(R.layout.dialog);
            builder.setTitle("Crear nuevo tema")
            builder.setMessage("¿Estás seguro de crear nuevo tema?")
            builder.setCancelable(true)

            builder.setNegativeButton("Cancelar", DialogInterface.OnClickListener{ dialog, which ->
                Toast.makeText(getActivity(),"Crear tema cancelado", Toast.LENGTH_LONG).show()
            })

            builder.setPositiveButton("Crear", DialogInterface.OnClickListener{ dialog, which ->
                Toast.makeText(getActivity(),"Creado el tema con éxito", Toast.LENGTH_LONG).show()
            })

            val alertDialog = builder.create()
            alertDialog.show();
        }


    }


}
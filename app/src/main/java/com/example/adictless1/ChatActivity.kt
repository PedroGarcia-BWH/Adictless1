package com.example.adictless1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adictless1.Controlador.Home
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_chat.*
import java.time.Instant


class ChatActivity : AppCompatActivity() {
    //var mensajes: List<Mensaje>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        var nickname: String = ""
        var cuerpoMensaje: String = ""
        var hora: String = ""
        var nomTema = getIntent().getExtras()?.getString("nombreTema").toString()
        var db = Firebase.firestore
        var foro = db.collection("foro").document(nomTema).collection("mensajes")
        var auth = Firebase.auth
        /*var mensaje = hashMapOf(
            "nickname" to nickname,
            "mensaje" to cuerpoMensaje,
            "hora" to hora,
        )
        var nMensaje = "0"
        var nMensajeContador = 1
        var mensajeEncontrado : Mensaje
        while(true)
        {
            foro.document(nMensaje).get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        foro.document(nMensaje).set(mensaje)
                        mensajes += Mensaje(mensaje[1],mensaje[2],mensaje[3])
                        Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                    } else {
                        Log.d(TAG, "No such document")
                        break //si no encuentra mas mensajes no busca mas
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }

            nMensajeContador++
            nMensaje = nMensajeContador.toString()
        }*/
        var enviar = findViewById<FloatingActionButton>(R.id.sendMesage)
        var nMensaje = "0"

        enviar.setOnClickListener() {
            var mensajeEnviar = findViewById<EditText>(R.id.Mensage)
            var horaActual = Instant.now().toString()
            var nicknameEnviar = "Prueba"
            var id = auth.currentUser.toString()
            val doc_ref = id.let { db.collection("users").document(id) }
            doc_ref.get()
                .addOnSuccessListener { document ->
                    if (document.data != null) {
                        //Log.d(Home.TAG, "Datos Recibidos desde la Base de Datos")
                        val data_user = document.data
                        nicknameEnviar = data_user?.get("username").toString()
                    }
                    var nMensajeContador = 1
                    var fin = true
                    while (true) {
                        foro.document(nMensaje).get()
                            .addOnSuccessListener { document ->
                                if (document != null) {
                                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                                } else {
                                    Log.d(TAG, "No such document")
                                    fin = false //si no encuentra mas mensajes no busca mas
                                }
                            }
                            .addOnFailureListener { exception ->
                                Log.d(TAG, "get failed with ", exception)
                            }

                        nMensajeContador++
                        nMensaje = nMensajeContador.toString()
                    }
                }

            val nuevoMensaje= hashMapOf(
                "nickname" to nicknameEnviar,
                "mensaje" to mensajeEnviar,
                "hora" to horaActual
            )
            db.collection("foro").document(nomTema).collection("mensajes").document(nMensaje)
                .set(nuevoMensaje)
               // .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
               // .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

        }

            /*fun initRecycler()
    {
        rvmensajes.layoutManager = LinearLayoutManager(this)
        val adapter = MensajeAdapter(mensajes)
        rvmensajes.adapter = adapter

    }*/
        }
        companion object{
            private const val TAG = "Mensaje"
        }

    /*fun initRecycler()
    {
    rvmensajes.layoutManager = LinearLayoutManager(this)
    val adapter = MensajeAdapter(mensajes)
    rvmensajes.adapter = adapter

    }*/

}
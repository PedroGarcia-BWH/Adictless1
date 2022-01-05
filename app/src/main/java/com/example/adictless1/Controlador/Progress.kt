package com.example.adictless1.Controlador

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.adictless1.ActivityProgress
import com.example.adictless1.Awards
import com.example.adictless1.NewsActivity
import com.example.adictless1.R
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.android.material.badge.BadgeUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_progress.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt
import kotlin.math.roundToLong

/**
 * A simple [Fragment] subclass.
 * Use the [Progress.newInstance] factory method to
 * create an instance of this fragment.
 */
class Progress : Fragment() {

    val db = FirebaseFirestore.getInstance()
    private lateinit var auth: FirebaseAuth

    companion object {
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
        return inflater.inflate(R.layout.fragment_progress, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        auth = Firebase.auth
        val user = auth.currentUser
        val doc_ref = user?.let { db.collection("users").document(it.uid) }
        doc_ref!!.get()
            .addOnSuccessListener { document ->
                if (document.data != null) {
                    Log.d(TAG, "Datos Recibidos desde la Base de Datos")
                    val data_user = document.data
                    val username = data_user?.get("username")
                    val login_usuario = view?.findViewById<TextView>(R.id.textView6)
                    login_usuario?.text = username.toString()

                    val level_db = data_user?.get("level").toString().toFloat()
                    val exp = (level_db % 1).toBigDecimal()

                    val level = level_db.toInt().toBigDecimal()
                    val level_usuario = view?.findViewById<TextView>(R.id.LvlTextView)
                    level_usuario?.text = "Nivel " + level

                    val multiplier = 100

                    val exp_total = level.multiply(BigDecimal(multiplier))
                    val exp_actual = exp.multiply(BigDecimal(multiplier)).multiply(level).setScale(0, BigDecimal.ROUND_HALF_UP)

                    val mostrar_exp = view?.findViewById<TextView>(R.id.textView7)
                    mostrar_exp?.text = "" + exp_actual + " EXP / " + exp_total + " EXP"

                    val progress_bar = view?.findViewById<ProgressBar>(R.id.progressBar)
                    progress_bar?.max = exp_total.toInt()
                    progress_bar?.progress = exp_actual.toInt()

                } else {
                    Log.d(TAG, "No existe dicho documento en la Base de Datos")
                    val usuario = "Invitado"

                    if (usuario == "Invitado") {
                        val logout = view?.findViewById<ImageButton>(R.id.logout)
                        val login_usuario = view?.findViewById<TextView>(R.id.textView6)
                        login_usuario?.text = usuario
                        logout!!.visibility = View.GONE
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }

        val addStatCard = view?.findViewById<CardView>(R.id.statsCardView)
        addStatCard?.setOnClickListener(){
            val statAc = Intent(activity, ActivityProgress::class.java)
            activity?.startActivity(statAc)
        }


        val addAwardCard = view?.findViewById<CardView>(R.id.awardsCardView)
        addAwardCard?.setOnClickListener(){
            val awardAc = Intent(activity, Awards::class.java)
            activity?.startActivity(awardAc)
        }

        //Ocultación de encuesta de progreso
        val EncProgress = view?.findViewById<CardView>(R.id.cardEncuestaProgreso)
        EncProgress!!.visibility = View.INVISIBLE

        //Boton realizar encuesta progreso
        val encButton = view?.findViewById<Button>(R.id.buttonEncuesta)
        encButton?.setOnClickListener(){
            Log.d("Visibilidad", EncProgress.visibility.toString())
            if(EncProgress.visibility == View.VISIBLE){
                EncProgress.visibility = View.INVISIBLE
                encButton.text = "Mostrar encuesta progreso"
            }
            else{
                EncProgress.visibility = View.VISIBLE
                encButton.text = "Ocultar encuesta progreso"
            }
        }

        //Enviar datos de encuesta
        val enviarEnc = view?.findViewById<Button>(R.id.buttonEnviar)
        enviarEnc?.setOnClickListener(){
            //Obtener fecha actual
            val fecha = LocalDate.now()
            //Comparar fecha con el ultimo registro de encuesta en la base de datos

            //Nivel = Obtener nivel de la base datos

            //OtorgarExperiencia(100xp + Nivel x 0.5)

            //Actualizar ultima fecha de realizacion en base de datos

        }


        //CERRAR SESION NO QUITAR
        val logout = view?.findViewById<ImageButton>(R.id.logout)
        logout?.setOnClickListener(){
            val builder = AlertDialog.Builder(getActivity())
            builder.setTitle("Cerrar Sesión")
            builder.setMessage("¿Estás seguro de cerrar sesión?")
            builder.setCancelable(true)

            builder.setNegativeButton("NO", DialogInterface.OnClickListener{ dialog, which ->
                Toast.makeText(getActivity(),"Cerrar sesión cancelado", Toast.LENGTH_LONG).show()
            })

            builder.setPositiveButton("Si", DialogInterface.OnClickListener{ dialog, which ->
                auth.signOut()
                getActivity()?.finish()
            })
            val alertDialog = builder.create()
            alertDialog.show();
        }
    }
}

/*
val addStatCard = view?.findViewById<CardView>(R.id.addHoursCardView)
addStatCard?.visibility = View.GONE

//Inicializar values con los valores de la base de datos
val values = Array<Float>(7) { Math.random().toFloat() * 2 }
//Inicializar gráfica
setBarChart(values)

val addButton = view?.findViewById<Button>(R.id.addStatButton)
addButton?.setOnClickListener() {
if (addStatCard?.visibility == View.GONE) {
addStatCard?.visibility = View.VISIBLE
} else {
addStatCard?.visibility = View.GONE
}
}

val addConfirmButton = view?.findViewById<Button>(R.id.addStatButton2)
addConfirmButton?.setOnClickListener() {
//Extraer dia de la semana
val day = LocalDate.now().dayOfWeek.ordinal
// *Comprobar el texto introducido
//Recoge los datos introducidos por el usuario
val time = view?.findViewById<EditText>(R.id.addTimeText)?.text.toString()

values[day] += time.toFloat()
setBarChart(values)
}
}
*/
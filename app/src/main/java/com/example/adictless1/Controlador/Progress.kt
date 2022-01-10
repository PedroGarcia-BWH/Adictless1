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
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import com.example.adictless1.ActivityProgress
import com.example.adictless1.Awards
import com.example.adictless1.Login
import com.example.adictless1.R
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_progress.*
import java.math.BigDecimal
import java.util.*
import kotlin.math.round
import kotlin.math.roundToInt
import android.app.Activity
import android.content.Intent.getIntent
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.FragmentTransaction
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId


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

        var fa: Fragment? = null
        fun onCreate() {
            fa = this
        }

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

                    val candado = view?.findViewById<Group>(R.id.group2)
                    candado!!.visibility = View.GONE

                } else {
                    Log.d(TAG, "No existe dicho documento en la Base de Datos")
                    val usuario = "Invitado"

                    if (usuario == "Invitado") {
                        val logout = view?.findViewById<ImageButton>(R.id.logout)
                        val login_usuario = view?.findViewById<TextView>(R.id.textView6)
                        login_usuario?.text = usuario
                        logout!!.visibility = View.GONE

                        val compForo = view?.findViewById<Group>(R.id.groupProgress)
                        compForo!!.visibility = View.GONE
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }

        val addStatCard = view?.findViewById<CardView>(R.id.statsCardView)
        addStatCard?.setOnClickListener() {
            val statAc = Intent(activity, ActivityProgress::class.java)
            activity?.startActivity(statAc)
            activity?.finish()
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
            val fechaT = Timestamp.now()

            //Setteamos a 0 las horas para solo tener en cuenta el dia
            val fecha = fechaT.toLocalDateTime()

            doc_ref.get()
                .addOnSuccessListener { document ->
                    if (document.data != null) {
                        Log.d(Progress.TAG, "Datos Recibidos desde la Base de Datos")
                        val data_user = document.data
                        val ultima_fechaD = data_user?.get("last_survey") as Timestamp   // Obtengo fecha de la base de datos

                        val ultima_fecha = ultima_fechaD.toLocalDateTime()

                        Log.d("Fecha - Base de datos", ultima_fecha.toString())
                        Log.d("Fecha - Actual", fecha.toString())

                        if(fecha.dayOfMonth > ultima_fecha.dayOfMonth && fecha.dayOfWeek == ultima_fecha.dayOfWeek){
                            // Compruebo que la fecha actual es posterior a la fecha almacenada en la base de datos. Este if es un poco useless ya que siempre va a ser true, pero por si acaso
                            val level_db = data_user.get("level").toString().toFloat()    // Obtengo nivel de la base de datos
                            val newLevel = ObtenerExperiencia(100,level_db,0.5f) // Calculo el nuevo nivel
                            val database = db.collection("users").document(user.uid)    // Obtengo el documento de la base de datos
                            database.update("level", newLevel)  // Se guarda el nuevo nivel en la base de datos
                            database.update("last_survey", Timestamp.now())  // Se guarda la fecha de realizacion de la encuesta en la base de datos
                            Toast.makeText(activity, "Encuesta Realizada", Toast.LENGTH_SHORT).show()  // Muestro un mensaje por pantalla
                            // El toast es pa ver que se esta ha hecho

                            (activity as Login).recreateFragment(this)  // Recreate Fragment
                        } else {
                            Toast.makeText(activity, "La encuesta es semanal. Inténtelo de nuevo dentro de 1 semana", Toast.LENGTH_SHORT).show()  // Muestro un mensaje por pantalla
                        }
                    }
                }
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
    @RequiresApi(Build.VERSION_CODES.O)
    fun Timestamp.toLocalDateTime(zone: ZoneId = ZoneId.systemDefault()) = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(seconds * 1000 + nanoseconds / 1000000), zone)
}

// Exp:         La experiencia que se le va a subir al usuario
// Level:       El nivel actual del usuario (sacar de la base de datos)
// Multiplier:  El numero que se va a multiplicar al nivel (0.5 o 2.5)

fun ObtenerExperiencia(exp: Int, level: Float, multiplier: Float): Float {
    var nivelActual = level.toInt()  // Nivel Actual del Usuario
    var expActualNivel = (level % 1) * 100 * nivelActual // Experiencia actual del usuario
    var expTotalNivel = nivelActual * 100   // Experiencia Total del Nivel
    val addExp = exp + (nivelActual * multiplier)  // Experiencia a añadir

    if((expActualNivel + addExp) >= expTotalNivel) {
        nivelActual += 1    // Incrementamos el Nivel
        expActualNivel = expActualNivel + addExp - expTotalNivel    // Añadimos la experiencia ganada a la experiencia actual del nivel
        expTotalNivel += 100    // Incremenramos la Experiencia Total

        val newExp = nivelActual + (expActualNivel / expTotalNivel) // La nueva experiencia será el nivel actual + experiencia del nivel
        return (newExp)
    } else {
        expActualNivel += addExp // Añadimos la experiencia ganada a la experiencia actual del nivel

        val newExp = nivelActual + (expActualNivel / expTotalNivel) // La nueva experiencia será el nivel actual + experiencia del nivel
        return (newExp)
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
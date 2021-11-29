package com.example.adictless1.Controlador

import android.app.AlertDialog
import android.content.DialogInterface
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
import com.example.adictless1.R
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_progress.*
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 * Use the [Progress.newInstance] factory method to
 * create an instance of this fragment.
 */
class Progress : Fragment() {

    val db = FirebaseFirestore.getInstance()
    private lateinit var auth: FirebaseAuth

    companion object{
        private var TAG = "DocSnippets"
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

        val addStatCard = view?.findViewById<CardView>(R.id.addHoursCardView)
        addStatCard?.visibility = View.GONE

        //Inicializar values con los valores de la base de datos
        val values = Array<Float>(7){ Math.random().toFloat() * 2}
        //Inicializar gráfica
        setBarChart(values)

        val addButton = view?.findViewById<Button>(R.id.addStatButton)
        addButton?.setOnClickListener(){
            if(addStatCard?.visibility == View.GONE){
                addStatCard?.visibility = View.VISIBLE
            }
            else{
                addStatCard?.visibility = View.GONE
            }
        }

        val addConfirmButton = view?.findViewById<Button>(R.id.addStatButton2)
        addConfirmButton?.setOnClickListener(){
            //Extraer dia de la semana
            val day = LocalDate.now().dayOfWeek.ordinal
            //*Comprobar el texto introducido
            //Recoge los datos introducidos por el usuario
            val time = view?.findViewById<EditText>(R.id.addTimeText)?.text.toString()

            values[day] += time.toFloat()
            setBarChart(values)
        }
    }

    private fun setBarChart(values : Array<Float> ){
        val entries = ArrayList<BarEntry>()

        for(value in values){
            entries.add(BarEntry(value, values.indexOf(value)))
        }

        /*entries.add(BarEntry(8f, 0))
        entries.add(BarEntry(2f, 1))
        entries.add(BarEntry(5f, 2))
        entries.add(BarEntry(20f, 3))
        entries.add(BarEntry(15f, 4))
        entries.add(BarEntry(19f, 5))
        entries.add(BarEntry(5f, 6))
         */

        val barDataSet = BarDataSet(entries, "Horas")

        val labels = ArrayList<String>()
        labels.add("Lun")
        labels.add("Mar")
        labels.add("Mie")
        labels.add("Jue")
        labels.add("Vie")
        labels.add("Sab")
        labels.add("Dom")
        val data = BarData(labels, barDataSet)
        barChart.data = data // set the data and list of lables into chart

        barChart.setDescription("Tiempo de uso semanal")  // set the description

        //barDataSet.setColors(ColorTemplate.COLORFUL_COLORS)
        barDataSet.color = resources.getColor(R.color.blueProgress)

        barChart.animateY(5000)


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

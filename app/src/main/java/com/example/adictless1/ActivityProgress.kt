package com.example.adictless1

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import android.R.id

import com.github.mikephil.charting.charts.BarChart
import kotlinx.android.synthetic.main.activity_progress.*


class ActivityProgress : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        //Inicializar values con los valores de la base de datos
        val values = Array<Float>(7) { Math.random().toFloat() * 2 }
        //Inicializar gráfica
        setBarChart(values, barChart)
    }
}

private fun setBarChart(values : Array<Float>,barChart : BarChart ){
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
    barDataSet.color = R.color.blueProgress

    barChart.animateY(5000)
}
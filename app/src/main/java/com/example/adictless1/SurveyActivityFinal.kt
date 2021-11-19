package com.example.adictless1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView

class SurveyActivityFinal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_final)

        val redesLayout = findViewById<LinearLayout>(R.id.RedesLayout)
        redesLayout.visibility = View.GONE

        val redesActivate = findViewById<RadioButton>(R.id.p3Yes)
        val redesDeactivate = findViewById<RadioButton>(R.id.p3No)

        redesActivate.setOnClickListener{
            redesLayout.visibility = View.VISIBLE
        }
        redesDeactivate.setOnClickListener{
            redesLayout.visibility = View.GONE
        }

        val apuestasLayout = findViewById<LinearLayout>(R.id.layoutApuesta)
        apuestasLayout.visibility = View.GONE

        val apuestasActivate = findViewById<RadioButton>(R.id.p10Yes)
        val apuestasDeactivate = findViewById<RadioButton>(R.id.p10No)

        apuestasActivate.setOnClickListener{
            apuestasLayout.visibility = View.VISIBLE
        }
        apuestasDeactivate.setOnClickListener{
            apuestasLayout.visibility = View.GONE
        }

        val juegosLayout = findViewById<LinearLayout>(R.id.layoutJuegos)
        juegosLayout.visibility = View.GONE

        val juegosActivate = findViewById<RadioButton>(R.id.p15Yes)
        val juegosDeactivate = findViewById<RadioButton>(R.id.p15No)

        juegosActivate.setOnClickListener{
            juegosLayout.visibility = View.VISIBLE
        }
        juegosDeactivate.setOnClickListener{
            juegosLayout.visibility = View.GONE
        }

        val continuar =findViewById<Button>(R.id.bottonSurvey)
        continuar.setOnClickListener {
            val continuarAct = Intent(this, RegisterActivity2::class.java)
            startActivity(continuarAct)
        }

    }
}
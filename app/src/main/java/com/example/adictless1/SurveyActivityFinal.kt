package com.example.adictless1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton

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
    }
}
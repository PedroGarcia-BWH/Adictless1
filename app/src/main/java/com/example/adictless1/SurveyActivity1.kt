package com.example.adictless1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView

class SurveyActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey1)

       /* val checkBoxYes = (CheckBox)findViewById(R.id.p3Yes)
        if(checkBoxYes.isChecked())
        {

            val redesSociales =findViewById<TextView>(R.id.bottonSurvey1)
            redesSociales.setOnClickListener{
                val intentRedesSociales = Intent(this,SurveyActivity2::class.java)
                startActivity(intentRedesSociales)
            }
        }
        val survey =findViewById<TextView>(R.id.survey)
        survey.setOnClickListener {
            val surveyAct = Intent(this, SurveyActivity1::class.java)
            startActivity(surveyAct)
        }*/
    }
}
package com.example.adictless1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class SurveyActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey1)

        val redesSociales =findViewById<TextView>(R.id.bottonSurvey1)
            redesSociales.setOnClickListener{
                val checkBoxYes = findViewById<RadioButton>(R.id.p3Yes)
                if(checkBoxYes.isChecked())
                {
                val intentRedesSociales = Intent(this,SurveyActivitySocialMedia1::class.java)
                startActivity(intentRedesSociales)
                }

       }

       /* val checkBoxNo = (RadioButton)findViewById(R.id.p3No)
        if(checkBoxNo.isChecked())
        {

            val redesSociales =findViewById<TextView>(R.id.bottonSurvey1)
            redesSociales.setOnClickListener{
                val intentRedesSociales = Intent(this,SurveyActivity2::class.java)
                startActivity(intentRedesSociales)
            }
        }*/
    }


    /*fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.p3Yes ->
                    if (checked) {
                        val redesSociales = findViewById<TextView>(R.id.bottonSurvey1)
                        redesSociales.setOnClickListener {
                            val intentRedesSociales =
                                Intent(this, SurveyActivitySocialMedia1::class.java)
                            startActivity(intentRedesSociales)
                        }
                    }
                R.id.p3No ->
                    if (checked) {
                        // Ninjas rule
                    }
            }
        }
    }*/
}
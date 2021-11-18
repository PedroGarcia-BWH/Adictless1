package com.example.adictless1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vishnusivadas.advanced_httpurlconnection.PutData

class RegisterActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)

        val regEmail = findViewById<TextView>(R.id.emailRegister)
        val regUsername = findViewById<TextView>(R.id.nickNameRegister)
        val regPassword = findViewById<TextView>(R.id.passwordRegister)
        val regRegistro = findViewById<Button>(R.id.confirmRegister)

        val progressBar = findViewById<ProgressBar>(R.id.reg_progress)

        regRegistro.setOnClickListener(View.OnClickListener {
            val email: String
            val username: String
            val password: String
            email = regEmail.getText().toString()
            username = regUsername.getText().toString()
            password = regPassword.getText().toString()
            if (email != "" && username != "" && password != "") {
                if(password.length >= 8) {
                    progressBar.setVisibility(View.VISIBLE)
                    val handler = Handler(Looper.getMainLooper())
                    handler.post {
                        val field = arrayOfNulls<String>(3)
                        field[0] = "email"
                        field[1] = "username"
                        field[2] = "password"
                        //Creating array for data
                        val data = arrayOfNulls<String>(3)
                        data[0] = email
                        data[1] = username
                        data[2] = password
                        val putData =
                            PutData(
                                "http://192.168.1.16/LoginRegister/signup.php",
                                "POST",
                                field,
                                data
                            )
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                progressBar.setVisibility(View.GONE)
                                val result = putData.result
                                if (result == "Registro Correcto") {
                                    Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT)
                                        .show()
                                    val intent =
                                        Intent(applicationContext, MainActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        "El password debe contener al menos 8 caracteres",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "Todos los campos son requeridos",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
        val survey =findViewById<TextView>(R.id.survey)
        survey.setOnClickListener {
            val surveyAct = Intent(this, SurveyActivity1::class.java)
            startActivity(surveyAct)
        }

    }
}
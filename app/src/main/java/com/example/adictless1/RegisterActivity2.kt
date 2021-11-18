package com.example.adictless1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
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
        val regRedes = findViewById<CheckBox>(R.id.redesSociales)
        val regApuestas = findViewById<CheckBox>(R.id.Apuestas)
        val regVideojuegos = findViewById<CheckBox>(R.id.Videojuegos)
        val regNose = findViewById<CheckBox>(R.id.NoSabe)
        val progressBar = findViewById<ProgressBar>(R.id.reg_progress)

        regRegistro.setOnClickListener(View.OnClickListener {
            val email: String
            val username: String
            val password: String
            var type: String = ""

            email = regEmail.getText().toString()
            username = regUsername.getText().toString()
            password = regPassword.getText().toString()
            if (email != "" && username != "" && password != "" && (regRedes.isChecked == true ||
                        regApuestas.isChecked == true || regVideojuegos.isChecked == true ||
                        regNose.isChecked == true)) {

                if (password.length >= 8) {

                    if (regNose.isChecked == true && (regRedes.isChecked == true ||
                                regApuestas.isChecked == true ||
                                regVideojuegos.isChecked == true)) {
                        Toast.makeText(
                            applicationContext,
                            "No se puede seleccionar el campo Prefiero No Contestar y otro campo a la vez",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else {
                        if (regNose.isChecked == true)
                            type += "NoProcede "
                        if (regRedes.isChecked == true)
                            type += "RedesSociales "
                        if (regApuestas.isChecked == true)
                            type += "Apuestas "
                        if (regVideojuegos.isChecked == true)
                            type += "Videojuegos"

                        progressBar.setVisibility(View.VISIBLE)
                        val handler = Handler(Looper.getMainLooper())
                        handler.post {
                            val field = arrayOfNulls<String>(4)
                            field[0] = "email"
                            field[1] = "username"
                            field[2] = "password"
                            field[3] = "type"
                            //Creating array for data
                            val data = arrayOfNulls<String>(4)
                            data[0] = email
                            data[1] = username
                            data[2] = password
                            data[3] = type

                            val putData =
                                PutData(
                                    "http://192.168.1.14/LoginRegister/signup.php",
                                    "POST",
                                    field,
                                    data
                                )
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE)
                                    val result = putData.result
                                    if (result == "Registro Correcto") {
                                        Toast.makeText(
                                            applicationContext,
                                            result,
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                        val intent =
                                            Intent(applicationContext, MainActivity::class.java)
                                        startActivity(intent)
                                        finish()
                                    } else {
                                        Toast.makeText(
                                            applicationContext,
                                            result,
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    }
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
        val survey = findViewById<TextView>(R.id.survey)
        survey.setOnClickListener {
            val surveyAct = Intent(this, SurveyActivity1::class.java)
            startActivity(surveyAct)
        }

    }
}
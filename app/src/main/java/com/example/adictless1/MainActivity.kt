package com.example.adictless1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.vishnusivadas.advanced_httpurlconnection.PutData


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val log_email = findViewById<TextView>(R.id.email)
        val log_password = findViewById<TextView>(R.id.password)
        val log_login = findViewById<Button>(R.id.login)
        val progressBar = findViewById<ProgressBar>(R.id.progress)

        log_login.setOnClickListener(View.OnClickListener {
            val email: String
            val password: String
            email = log_email.getText().toString()
            password = log_password.getText().toString()
            if (email != "" && password != "") {
                progressBar.setVisibility(View.VISIBLE)
                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    val field = arrayOfNulls<String>(2)
                    field[0] = "email"
                    field[1] = "password"
                    //Creating array for data
                    val data = arrayOfNulls<String>(2)
                    data[0] = email
                    data[1] = password

                    val putData =
                        PutData("http://192.168.1.16/LoginRegister/login.php", "POST", field, data)
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            progressBar.setVisibility(View.GONE)
                            val result = putData.result
                            if (result == "Inicio de Sesion Correcto") {
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT)
                                    .show()
                                val intent = Intent(applicationContext, Login::class.java)
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
                    "Todos los campos son requeridos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        val registro =findViewById<Button>(R.id.register)
        registro.setOnClickListener {
            val intento1 = Intent(this, RegisterActivity1::class.java)
            startActivity(intento1)
        }
    }
}
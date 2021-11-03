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

        val logEmail = findViewById<TextView>(R.id.email)
        val logPassword = findViewById<TextView>(R.id.password)
        val logLogin = findViewById<Button>(R.id.login)
        val progressBar = findViewById<ProgressBar>(R.id.progress)

        logLogin.setOnClickListener(View.OnClickListener {
            val email: String
            val password: String
            email = logEmail.getText().toString()
            password = logPassword.getText().toString()
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
                        PutData("http://10.182.116.204/LoginRegister/login.php", "POST", field, data)
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            progressBar.setVisibility(View.GONE)
                            val result = putData.result
                            if (result == "Inicio de Sesion Correcto") {
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT)
                                    .show()
                                val intent = Intent(this, Login::class.java)
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
            val intento2 = Intent(this, Login::class.java)
            startActivity(intento1)
        }
    }
}
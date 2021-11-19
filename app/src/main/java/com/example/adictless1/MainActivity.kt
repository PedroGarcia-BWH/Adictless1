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
            var username: CharSequence = ""
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
                        PutData("http://192.168.1.16/LoginRegister/login.php", "POST", field, data)
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            progressBar.setVisibility(View.GONE)
                            val result = putData.result
                            if (result == "Inicio de Sesion Correcto") {
                                val fetchData = PutData("http://192.168.1.16/LoginRegister/show_login.php","POST", field, data)
                                if(fetchData.startPut()){
                                    if(fetchData.onComplete()){
                                        username = fetchData.result
                                    }
                                }
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT)
                                    .show()
                                val intent = Intent(applicationContext, Login::class.java)
                                intent.putExtra("usuario",username)
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

        val registro =findViewById<TextView>(R.id.register)
        registro.setOnClickListener {
            val registerAct = Intent(this, RegisterActivity1::class.java)
            startActivity(registerAct)
        }

        val invitado = findViewById<TextView>(R.id.guest)
        invitado.setOnClickListener {
            val guestAct = Intent(this, Login::class.java)
            startActivity(guestAct)
        }
    }
}
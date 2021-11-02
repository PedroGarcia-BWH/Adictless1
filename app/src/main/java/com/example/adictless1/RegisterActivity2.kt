package com.example.adictless1

import androidx.appcompat.app.AppCompatActivity
import android.widget.ProgressBar
import android.os.Bundle
import android.os.Looper
import com.vishnusivadas.advanced_httpurlconnection.PutData
import android.widget.Toast
import android.content.Intent
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView

class RegisterActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)

        val reg_email = findViewById<TextView>(R.id.emailRegister)
        val reg_username = findViewById<TextView>(R.id.nickNameRegister)
        val reg_password = findViewById<TextView>(R.id.passwordRegister)
        val reg_registro = findViewById<Button>(R.id.confirmRegister)
        val progressBar = findViewById<ProgressBar>(R.id.reg_progress)

        reg_registro.setOnClickListener(View.OnClickListener {
            val email: String
            val username: String
            val password: String
            email = reg_email.getText().toString()
            username = reg_username.getText().toString()
            password = reg_password.getText().toString()
            if (email != "" && username != "" && password != "") {
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
                        PutData("http://10.182.116.204/LoginRegister/signup.php", "POST", field, data)
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            progressBar.setVisibility(View.GONE)
                            val result = putData.result
                            if (result == "Registro Correcto") {
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT)
                                    .show()
                                val intent = Intent(applicationContext, MainActivity::class.java)
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
    }
}
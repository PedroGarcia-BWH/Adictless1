package com.example.adictless1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

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
                InicioSesion(email,password)
                progressBar.setVisibility(View.GONE)
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
            auth.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInAnonymously:success")
                        val user = auth.currentUser
                        updateUI(user)
                        Toast.makeText(baseContext, "Inicio de Sesión Correcto",
                            Toast.LENGTH_SHORT).show()
                        updateUI(user)
                        val invitado = Intent(applicationContext, Login::class.java)
                        startActivity(invitado)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInAnonymously:failure", task.exception)
                        Toast.makeText(baseContext, "Fallo del Login de Invitado",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }
    }
    companion object{
        private const val TAG = "EmailPassword"
    }

    fun InicioSesion(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    Toast.makeText(baseContext, "Inicio de Sesión Correcto",
                        Toast.LENGTH_SHORT).show()
                    updateUI(user)
                    val username = user?.email

                    val login = Intent(applicationContext, Login::class.java)
                    login.putExtra("usuario",username)
                    startActivity(login)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Correo o Password Incorrecto",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {

    }

    private fun reload() {

    }
}
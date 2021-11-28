package com.example.adictless1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity2 : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)

        auth = Firebase.auth

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
                    if (regNose.isChecked == true)
                        type += "NoProcede "
                    if (regRedes.isChecked == true)
                        type += "RedesSociales "
                    if (regApuestas.isChecked == true)
                        type += "Apuestas "
                    if (regVideojuegos.isChecked == true)
                        type += "Videojuegos"

                    progressBar.setVisibility(View.VISIBLE)
                    crearCuenta(email,username,password,type)
                    progressBar.setVisibility(View.GONE)

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
            val surveyAct = Intent(this, SurveyActivityFinal::class.java)
            startActivity(surveyAct)
        }


        /* Intercambio de bloqueos entre checkbox */
        val noResponse = findViewById<CheckBox>(R.id.NoSabe)

        noResponse.setOnCheckedChangeListener { buttonView, isChecked ->
            regRedes.isEnabled = !isChecked
            regApuestas.isEnabled = !isChecked
            regVideojuegos.isEnabled = !isChecked
        }

        regRedes.setOnCheckedChangeListener { buttonView, isChecked ->
            noResponse.isEnabled = !isChecked && !regApuestas.isChecked && !regVideojuegos.isChecked
        }
        regApuestas.setOnCheckedChangeListener { buttonView, isChecked ->
            noResponse.isEnabled = !isChecked && !regRedes.isChecked && !regVideojuegos.isChecked
        }
        regVideojuegos.setOnCheckedChangeListener { buttonView, isChecked ->
            noResponse.isEnabled = !isChecked && !regApuestas.isChecked && !regRedes.isChecked
        }
    }

    companion object{
        private var TAG = "EmailPassword"
    }

    fun crearCuenta (email : String, username: String, password: String, type: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    Toast.makeText(baseContext, "Registro Correcto",
                        Toast.LENGTH_SHORT).show()
                    updateUI(user)

                    val usuario = hashMapOf(
                        "email" to email,
                        "username" to username,
                        "password" to password,
                        "type" to type
                    )
                    TAG = "DocSnippets"
                    db.collection("users").document(username)
                        .set(usuario)
                        .addOnSuccessListener { Log.d(TAG, "Documento escrito de forma exitosa") }
                        .addOnFailureListener { e -> Log.w(TAG, "Error al escribir el documento", e) }

                    val registro = Intent(applicationContext, MainActivity::class.java)
                    startActivity(registro)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Fallo de Registro",
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
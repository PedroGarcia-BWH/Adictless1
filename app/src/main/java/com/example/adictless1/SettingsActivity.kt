package com.example.adictless1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    class SettingsFragment : PreferenceFragmentCompat() {

        val db = FirebaseFirestore.getInstance()
        private lateinit var auth: FirebaseAuth

        companion object {
            private var TAG = "EmailPassword"
        }

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            auth = Firebase.auth
            val user = auth.currentUser

            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)

            val newEmail = sharedPreferences.getString("Modificaremail", "")
            val newPassword = sharedPreferences.getString("Modificarcontrasena", "")
            val newUser = sharedPreferences.getString("Modificaruser", "")
        }

        /*
       if(newEmail != null || newEmail != "" && (db.collection("users").whereEqualTo("email",newEmail)) == null){

           user!!.updateEmail(newEmail.toString())
               .addOnCompleteListener { task ->
                   if (task.isSuccessful) {
                       Log.d(TAG, "Correo del Usuario Actualizado.")
                   }
               }
           val usuario = hashMapOf("email" to newEmail.toString())
           db.collection("users").document(user.uid)
               .set(usuario, SetOptions.merge())
       }

       if(newPassword != null || newPassword != "" && (db.collection("users").whereEqualTo("password",newPassword)) == null){
           auth = Firebase.auth
           val user = auth.currentUser

           user!!.updatePassword(newPassword.toString())
               .addOnCompleteListener { task ->
                   if (task.isSuccessful) {
                       Log.d(TAG, "Password del Usuario Actualizado.")
                   }
               }
           val usuario = hashMapOf("password" to newPassword.toString())
           db.collection("users").document(user.uid)
               .set(usuario, SetOptions.merge())
       }

       if(newUser != null || newUser != "" && (db.collection("users").whereEqualTo("username",newUser)) == null){
           auth = Firebase.auth
           val user = auth.currentUser

           val usuario = hashMapOf("username" to newUser.toString())
           if (user != null) {
               db.collection("users").document(user.uid)
                   .set(usuario, SetOptions.merge())
               Log.d(TAG, "Username Actualizado.")
           }
       }

         */

        }
        private fun updateUI(user: FirebaseUser?) {

        }
    }
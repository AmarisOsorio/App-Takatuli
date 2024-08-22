package expo.turismo.takatuli

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import expo.turismo.takatuli.Modelo.ClaseConexion
import expo.turismo.takatuli.Modelo.DataclassUsuarios
import expo.turismo.takatuli.Modelo.dataclassRoles
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.MessageDigest

class RecuperacionDecontrasenia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recuperacion_decontrasenia)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val NuevaContra = findViewById<EditText>(R.id.txtNuevaContra)
        val btnActuContra = findViewById<Button>(R.id.btnActuContra)
        val correoGlobal = RecuperacionDePassword.variablesGlobalesRecuperacion.correoIngresado

        fun hashSHA256(contraseniaEscrita: String): String{
            val bytes = MessageDigest.getInstance("SHA-256").digest(contraseniaEscrita.toByteArray())
            return bytes.joinToString("") { "%02x".format(it) }
        }


        btnActuContra.setOnClickListener {


            GlobalScope.launch (Dispatchers.IO) {
                val objConexion = ClaseConexion().cadenaConexion()

                val contrasenaEncripta = hashSHA256(NuevaContra.text.toString())



                val UpdateContra = objConexion?.prepareStatement("update tbUsuario set Password_Usuario = ? where Correo_Usuario = ?")!!
                UpdateContra.setString(1, contrasenaEncripta)
                UpdateContra.setString(2, RecuperacionDePassword.correoIngresado)
                UpdateContra.executeUpdate()

                val commit = objConexion?.prepareStatement("commit")!!
                commit.executeUpdate()

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RecuperacionDecontrasenia, "Contraseña Actualizada", Toast.LENGTH_SHORT).show()

                }

            }

            val intent = Intent(this@RecuperacionDecontrasenia, Login::class.java)
            startActivity(intent)

        }


    }
}
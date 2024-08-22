package expo.turismo.takatuli

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import expo.turismo.takatuli.Modelo.ClaseConexion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.security.MessageDigest

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtUsuario = findViewById<EditText>(R.id.txtUsuario)
        val txtContrasena = findViewById<EditText>(R.id.txtContrasenia)
        val btnIniciarSession = findViewById<Button>(R.id.btnIniciarSession)
        val btnRegistrarse = findViewById<Button>(R.id.BtnRegistrarse)
        val imgRecuContra = findViewById<TextView>(R.id.imgRecuContra)

        fun hashSHA256(contraseniaEscrita: String): String{
            val bytes = MessageDigest.getInstance("SHA-256").digest(contraseniaEscrita.toByteArray())
            return bytes.joinToString("") { "%02x".format(it) }
        }


        btnIniciarSession.setOnClickListener {

            GlobalScope.launch(Dispatchers.IO){

                val objConexion = ClaseConexion().cadenaConexion()


                val contrasenaEncripta = hashSHA256(txtContrasena.text.toString())


                val Usuario = txtUsuario.text.toString()
                val contrasena = txtContrasena.text.toString()
                var hayErrores = false

                val comprobarUsuario = objConexion?.prepareStatement("Select * from tbUsuario Where Nombre_Usuario = ? and Password_Usuario = ?")!!
                comprobarUsuario.setString(1, txtUsuario.text.toString())
                comprobarUsuario.setString(2,contrasenaEncripta)

                val resultado = comprobarUsuario.executeQuery()

                if(resultado.next()){
                    val intent = Intent(this@Login, MainActivity::class.java)
                    startActivity(intent)
                }
                else{
                    println("Usuario no encontrado, verifique las credenciales")
                }


                if(Usuario.isEmpty()){
                    txtUsuario.error = "El usuario es obligatorio"
                    hayErrores = true
                } else {
                    txtUsuario.error = null

                }


                if(contrasena.isEmpty()) {
                    txtContrasena.error = "La contraseña es obligatoria"
                    hayErrores = true
                } else {
                    txtContrasena.error = null

                }


            }

        }

        btnRegistrarse.setOnClickListener {
            val intent = Intent(this@Login, RegistrarUsuarios::class.java)
            startActivity(intent)
        }

        imgRecuContra.setOnClickListener(){
            val intent = Intent(this@Login, RecuperacionDePassword::class.java)
            startActivity(intent)
        }

    }
}
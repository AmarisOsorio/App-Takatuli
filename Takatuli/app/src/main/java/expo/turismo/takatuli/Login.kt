package expo.turismo.takatuli

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
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
import kotlinx.coroutines.withContext
import java.security.MessageDigest

class Login : AppCompatActivity() {

    /*
    *
    *
    * */

    companion object loginGlobal {
        lateinit var nombreusuario: String

    }

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
        val txtRecuContra = findViewById<TextView>(R.id.imgRecuContra)
        val imgVerContra = findViewById<ImageView>(R.id.imgVerContra)


        // Encriptacion //
        fun hashSHA256(contraseniaEscrita: String): String{
            val bytes = MessageDigest.getInstance("SHA-256").digest(contraseniaEscrita.toByteArray())
            return bytes.joinToString("") { "%02x".format(it) }
        }


        btnIniciarSession.setOnClickListener {
            nombreusuario = txtUsuario.text.toString()

            var hayErrores = false
            val Usuario = txtUsuario.text.toString()
            val contrasena = txtContrasena.text.toString()


            if (Usuario.isEmpty()) {
                txtUsuario.error = "El nombre es obligatorio"
                hayErrores = true
            } else {
                txtUsuario.error = null
            }
            if (contrasena.isEmpty()) {
                txtContrasena.error = "La contraseña es obligatoria"
                hayErrores = true
            } else {
                txtContrasena.error = null
            }

            GlobalScope.launch(Dispatchers.IO) {

                val objConexion = ClaseConexion().cadenaConexion()



                val contrasenaEncripta = hashSHA256(txtContrasena.text.toString())


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


            }


        }

        fun guardarDatos(
            Usuario: String,
            contrasena: String
        ) {

            Toast.makeText(this, "¡Bienvenido!", Toast.LENGTH_SHORT).show()
        }




        btnRegistrarse.setOnClickListener {
            val intent = Intent(this@Login, RegistrarUsuarios::class.java)
            startActivity(intent)
        }

        txtRecuContra.setOnClickListener(){
            val intent = Intent(this@Login, RecuperacionDePassword::class.java)
            startActivity(intent)
        }

        ////////////////////// Botones para ver contraseña /////////////////////////////////

        imgVerContra.setOnClickListener {
            if (txtContrasena.inputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                txtContrasena.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                txtContrasena.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }
    }
}
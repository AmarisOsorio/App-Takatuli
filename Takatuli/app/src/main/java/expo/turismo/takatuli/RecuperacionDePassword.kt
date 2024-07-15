package expo.turismo.takatuli

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecuperacionDePassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recuperacion_de_password)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtCorreo = findViewById<EditText>(R.id.txtEmail)
        val btnEnviar = findViewById<Button>(R.id.btnEnviar)
        val txtCodigo = findViewById<EditText>(R.id.txtVerificarCodigo)
        val btnValidar = findViewById<Button>(R.id.btnVerificar)


        val codigoAleatorio = (100000..999999).random()


        btnEnviar.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {

                val CodigoRandom = codigoAleatorio.toString()


                enviarCorreo("${txtCorreo.text}", "Recuperación de contraseña", "Este es tu codigo de recuperacion ${CodigoRandom}")
            }
        }


        btnValidar.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                val VerficacionCodigo = codigoAleatorio.toString()

                if(VerficacionCodigo == txtCodigo.text.toString()){
                    val pantallaRecu = Intent(this@RecuperacionDePassword, RecuperacionDecontrasenia::class.java)
                    startActivity(pantallaRecu)

                }

                else{
                    Toast.makeText(this@RecuperacionDePassword, "Codigo Incorrecto", Toast.LENGTH_SHORT).show()
                }
            }

        }


    }
}
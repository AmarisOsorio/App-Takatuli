package expo.turismo.takatuli

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import expo.turismo.takatuli.Modelo.ClaseConexion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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


        btnActuContra.setOnClickListener {


            GlobalScope.launch (Dispatchers.IO) {
                val objConexion = ClaseConexion().cadenaConexion()

                val UpdateContra = objConexion?.prepareStatement("update tbUsuario")


            }

        }
    }
}
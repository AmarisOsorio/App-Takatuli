package expo.turismo.takatuli

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Politicas_de_Privacidad : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_politicas_de_privacidad)

        val Aceptar = findViewById<Button>(R.id.btnAceptar)
        Aceptar.setOnClickListener {
            val intent= Intent(this,fragment_ajustes::class.java)
                startActivity(intent)
        }
    }

}
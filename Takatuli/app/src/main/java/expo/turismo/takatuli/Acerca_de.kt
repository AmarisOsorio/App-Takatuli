package expo.turismo.takatuli

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Acerca_de : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_acerca_de)

        val Aceptarb = findViewById<Button>(R.id.btnAceptar2 )
        Aceptarb.setOnClickListener {
            val intent= Intent(this,fragment_ajustes::class.java)
            startActivity(intent)
        }











































        getSupportActionBar()?.hide()



        }
    }

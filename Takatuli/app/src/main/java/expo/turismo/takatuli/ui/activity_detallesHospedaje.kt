package expo.turismo.takatuli.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import expo.turismo.takatuli.MainActivity
import expo.turismo.takatuli.R

class activity_detallesHospedaje : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalles_hospedaje)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

       val Precio_HospedajeRecibido = intent.getStringExtra("Precio_Hospedaje")
        val nombreRecibidoHospedaje = intent.getStringExtra("Nombre_Hospedaje ")
        val detallesRecibidoHospedaje = intent.getStringExtra("Detalles_Hospedaje ")
        val fotoRecibidaHospedaje = intent.getStringExtra("Fotos_Hospedaje ")


        //Mando a llamar a todos los elementos de la pantalla
        val txtPrecioHospedajeDeta = findViewById<TextView>(R.id.txtdetaPrecio)
        val txtNombreDetalleHospedaje = findViewById<TextView>(R.id.txtdetaNombreHospedaje)
        val ImgHospedajeRecivida = findViewById<ImageView>(R.id.imgdetaHospedaje)
        val txtdetallesHospedaje = findViewById<TextView>(R.id.txtdetallesHospedaje)
        val btnatrasdetallesH = findViewById<ImageView>(R.id.imgatrasdetallesH)

        btnatrasdetallesH.setOnClickListener {
            val iratrasdetallesH = Intent(this, MainActivity::class.java)
            startActivity(iratrasdetallesH)
        }


        //Asigarle los datos recibidos a mis TextView
        txtPrecioHospedajeDeta.text = Precio_HospedajeRecibido
        txtNombreDetalleHospedaje.text = nombreRecibidoHospedaje
        txtdetallesHospedaje.text = detallesRecibidoHospedaje
        ImgHospedajeRecivida.setImageResource(fotoRecibidaHospedaje!!.toInt())
    }
}
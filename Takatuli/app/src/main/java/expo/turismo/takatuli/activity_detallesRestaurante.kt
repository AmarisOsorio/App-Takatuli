package expo.turismo.takatuli

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_detallesRestaurante : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalles_restaurante)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Recibir los valores
        val fotoMenuRecibida = intent.getStringExtra("Foto_Menu")
        val nombreRecibidoRestaurante = intent.getStringExtra("Nombre_Restaurante ")
        val detallesRecibidoRestaurante = intent.getStringExtra("Menu_Restaurante ")
        val fotoRecibidaRestaurantee = intent.getStringExtra("Foto_Resturantes ")
        val btnatrasR = findViewById<ImageView>(R.id.imgatrasdetallesR)

        btnatrasR.setOnClickListener {
            val iratrasdetallesR = Intent(this, MainActivity::class.java)
            startActivity(iratrasdetallesR)
        }

        //Mando a llamar a todos los elementos de la pantalla
        val ImgfotoMenuRecibida = findViewById<ImageView>(R.id.imgdetaRestauranteMenu)
        val txtnombreRestaurante = findViewById<TextView>(R.id.txtdetaNombreRestaurante)
        val ImgfotoRestaurante = findViewById<ImageView>(R.id.imgdetaRestaurante)
        val txtdetallesRestaurante = findViewById<TextView>(R.id.txtdetaMenuRestaurante)

        //Asigarle los datos recibidos a mis TextView
        txtnombreRestaurante.text = nombreRecibidoRestaurante
        txtdetallesRestaurante.text = detallesRecibidoRestaurante
        //ImgfotoRestaurante.setImageResource(fotoRecibidaRestaurantee!!.toInt())
        //ImgfotoMenuRecibida.setImageResource(fotoMenuRecibida!!.toInt())


    }
}
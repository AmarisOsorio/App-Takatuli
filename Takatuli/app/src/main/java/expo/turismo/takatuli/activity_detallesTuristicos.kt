package expo.turismo.takatuli

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class activity_detallesTuristicos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalles_turisticos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

       // val UUIDRecibido = intent.getStringExtra("UUID_LugarTuristico ")
        val nombreRecibido = intent.getStringExtra("Nombre_LugarTuristico")
        val detallesRecibido = intent.getStringExtra("Detalles_Lugar_Turistico")
        val fotoRecibida = intent.getStringExtra("Fotos_Lugar_Turistico")


        //Mando a llamar a todos los elementos de la pantalla
        //val txtUUIDDetalle = findViewById<TextView>(R.id.txtdetallesuuidTuristico)
        val txtNombreDetalle = findViewById<TextView>(R.id.txtDetaNombreTuristico)
        val ImgTuristicaRecivida = findViewById<ImageView>(R.id.imgDetaTuristico)
        val txtdetallesTurisitcos = findViewById<TextView>(R.id.txtDetallesTuristico)
        val btnatrasT = findViewById<ImageView>(R.id.imgatrasdetallesT)

        btnatrasT.setOnClickListener {
            val iratrasdetallesT = Intent(this, MainActivity::class.java)
            startActivity(iratrasdetallesT)
        }

        //Asigarle los datos recibidos a mis TextView
        //txtUUIDDetalle.text = UUIDRecibido
        txtNombreDetalle.text = nombreRecibido
        txtdetallesTurisitcos.text = detallesRecibido
        Glide.with(this).load(fotoRecibida).into(ImgTuristicaRecivida)
    }


}
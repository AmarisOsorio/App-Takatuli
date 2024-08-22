package expo.turismo.takatuli

import RecyclerViewHelper.AdaptadorHospedaje
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.Modelo.ClaseConexion
import expo.turismo.takatuli.Modelo.tbHospedaje
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HospedajesAgregadsos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hospedajes_agregadsos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imgAtrasHospedaje = findViewById<ImageView>(R.id.imgAtrasHospedaje)
        val rcvHospedaje = findViewById<RecyclerView>(R.id.rcvHospedaje)
        rcvHospedaje.layoutManager = LinearLayoutManager(this)

        fun obtenerDatosHospedaje(): List<tbHospedaje> {
            val objConexion = ClaseConexion().cadenaConexion()

            val statement = objConexion?.createStatement()
            val resulSet = statement?.executeQuery("select * from tbHospedaje")!!

            val Hospedaje = mutableListOf<tbHospedaje>()
            while (resulSet?.next() == true) {
                val UUID_Hospedaje = resulSet.getString("UUID_Hospedaje")
                val Nombre_Hospedaje = resulSet.getString("Nombre_Hospedaje")
                val Precio_Hospedaje = resulSet.getInt("Precio_Hospedaje")
                val Detalles_Hospedaje = resulSet.getString("Detalles_Hospedaje")

                val Hospedajes = tbHospedaje(UUID_Hospedaje, Nombre_Hospedaje, Precio_Hospedaje, Detalles_Hospedaje)
                Hospedaje.add(Hospedajes)
            }
            return Hospedaje
        }

        CoroutineScope(Dispatchers.IO).launch {
            val HospedajesDB = obtenerDatosHospedaje()
            withContext(Dispatchers.Main){
                val miAdapterH = AdaptadorHospedaje(HospedajesDB)
                rcvHospedaje.adapter = miAdapterH
            }
        }

    }
}
package expo.turismo.takatuli

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.Modelo.ClaseConexion
import expo.turismo.takatuli.Modelo.tbHospedaje
import expo.turismo.takatuli.RecyclerViewHost.AdaptadorHost
import expo.turismo.takatuli.RecyclerViewMostrar.Adaptador
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class rcvhospedajes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rcvhospedajes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rcvhospedajes = findViewById<RecyclerView>(R.id.rcvHospedaje)
        rcvhospedajes.layoutManager = LinearLayoutManager(this)

        fun obtenerHospedaje(): List<tbHospedaje> {
            val objConexion = ClaseConexion().cadenaConexion()
            val statement = objConexion?.createStatement()
            val resulset = statement?.executeQuery("Select * from tbHospedaje")!!

            val listaHost= mutableListOf<tbHospedaje>()

            while (resulset.next()) {
                val UUID_Hospedaje = resulset.getString("UUID_Hospedaje")
                val Nombre_Hospedaje = resulset.getString("Nombre_Hospedaje")
                val Precio_Hospedaje = resulset.getDouble("Precio_Hospedaje")
                val Detalles_Hospedaje = resulset.getString("Detalles_Hospedaje")
                val Fotos_Hospedaje = resulset.getString("Fotos_Hospedaje")

                val valoresJuntos = tbHospedaje(
                    UUID_Hospedaje,
                    Nombre_Hospedaje,
                    Precio_Hospedaje,
                    Detalles_Hospedaje,
                    Fotos_Hospedaje
                )


                listaHost.add(valoresJuntos)
            }

            return listaHost
        }

        GlobalScope.launch(Dispatchers.IO) {
            val TakatuliBD2 = obtenerHospedaje()
            withContext(Dispatchers.Main) {
                val adapter = AdaptadorHost(TakatuliBD2)
                rcvhospedajes.adapter = adapter
            }
        }
    }
}


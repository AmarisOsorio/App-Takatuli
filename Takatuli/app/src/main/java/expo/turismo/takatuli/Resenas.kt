package expo.turismo.takatuli

import RecyclerViewResena.AdaptadorResena
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import expo.turismo.takatuli.Modelo.ClaseConexion
import expo.turismo.takatuli.Modelo.tbResena
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class Resenas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resenas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtResena = findViewById<TextView>(R.id.txtResenaa)
        val btnGuardarR = findViewById<Button>(R.id.btnGuardarR)
        val rcvResenas = findViewById<RecyclerView>(R.id.rcvResena)

        rcvResenas.layoutManager = LinearLayoutManager(this)

        fun obtenerResenas(): List<tbResena> {
            val objConexion = ClaseConexion().cadenaConexion()
            val statement = objConexion?.createStatement()
            val resulset = statement?.executeQuery("SELECT * FROM tbReseñas")!!

            val listaResenas = mutableListOf<tbResena>()
            while (resulset.next()) {
                val UUID_Reseña = resulset.getString("UUID_Reseña")
                val Reseña_Viaje = resulset.getString("Reseña_Viaje")

                val valoresJuntos = tbResena(UUID_Reseña, Reseña_Viaje)
                listaResenas.add(valoresJuntos)
            }
            return listaResenas
        }

        CoroutineScope(Dispatchers.IO).launch {
            val ResenasBd = obtenerResenas()
            withContext(Dispatchers.Main) {
                val adaptador = AdaptadorResena(ResenasBd)
                rcvResenas.adapter = adaptador
            }
        }

        btnGuardarR.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val objConexion = ClaseConexion().cadenaConexion()
                val addResena =
                    objConexion?.prepareStatement("insert into tbReseñas (UUID_Reseña, Reseña_Viaje) values(?,?)")!!
                addResena.setString(1, UUID.randomUUID().toString())
                addResena.setString(2, txtResena.text.toString())
                addResena.executeUpdate()
            }
        }
    }
}